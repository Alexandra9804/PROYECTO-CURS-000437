package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.consumers.message.AlquilerValidacionMessage;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.document.AlquilerValidacionMessageDocument;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.exceptions.AlquilerValidacionConsumerException;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.repository.AlquilerValidacionMessageRepository;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.AlquilerValidacionService;

@Slf4j
@Component
@RequiredArgsConstructor
public class AlquilerValidacionConsumer {

    private final ObjectMapper objectMapper;
    private final AlquilerValidacionService alquilerValidacionService;
    private final AlquilerValidacionMessageRepository messageRepository;

    @RetryableTopic(
            include = {AlquilerValidacionConsumerException.class},
            attempts = "4",
            backoff = @Backoff(delay = 3000, multiplier = 2),
            retryTopicSuffix = "-retry",
            dltTopicSuffix = "-dlt",
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE
    )
    @KafkaListener(
            topics = "${custom.topic.alquiler.validacion}",
            groupId = "${custom.group.alquiler-validacion}",
            autoStartup = "${custom.kafka.consumer.alquiler-validacion-enabled:true}"
    )
    public void readMessage(
            String message,
            @Header(value = KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(value = KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(value = KafkaHeaders.OFFSET) long offset
    ) {

        log.info("Mensaje recibido desde Kafka. topic={}, partition={}, offset={}, message={}",
                topic, partition, offset, message);

        try {
            AlquilerValidacionMessage validacionMessage =
                    objectMapper.readValue(message, AlquilerValidacionMessage.class);

            if (validacionMessage.getIdAlquilerEstado() == null) {
                throw new AlquilerValidacionConsumerException(
                        "El idAlquilerEstado es obligatorio para actualizar el alquiler"
                );
            }

            log.info("Mensaje convertido: {}", validacionMessage);

            alquilerValidacionService.actualizarEstadoAlquiler(validacionMessage);

        } catch (AlquilerValidacionConsumerException e) {
            throw e;
        } catch (Exception e) {
            throw new AlquilerValidacionConsumerException(
                    "Error procesando mensaje de validación de alquiler", e
            );
        }
    }

    @DltHandler
    public void dltHandler(
            String message,
            @Header(value = KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(value = KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(value = KafkaHeaders.OFFSET) long offset,
            @Header(value = KafkaHeaders.EXCEPTION_MESSAGE) String errorMessage
    ) throws JsonProcessingException {
        log.error("Mensaje enviado a DLT. topic={}, partition={}, offset={}, error={}, message={}",
                topic, partition, offset, errorMessage, message);

        AlquilerValidacionMessage validacionMessage = objectMapper.readValue(
                message,
                AlquilerValidacionMessage.class
        );

        AlquilerValidacionMessageDocument document =
                AlquilerValidacionMessageDocument.builder()
                        .idAlquiler(validacionMessage.getIdAlquiler())
                        .idVehiculo(validacionMessage.getIdVehiculo())
                        .idAlquilerEstado(validacionMessage.getIdAlquilerEstado())
                        .mensaje(validacionMessage.getMensaje())
                        .fecha(validacionMessage.getFecha())
                        .situacion("DLT")
                        .estado("ERROR")
                        .build();

        messageRepository.save(document);
    }
}