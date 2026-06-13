package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.consumers;

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
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.exceptions.AlquilerVehiculoConsumerException;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.message.AlquilerMessage;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.services.VehiculoAlquilerService;

@Slf4j
@RequiredArgsConstructor
@Component
public class AlquilerVehiculoConsumer {
    private final ObjectMapper objectMapper;
    private final VehiculoAlquilerService vehiculoAlquilerService;

    @RetryableTopic(
            include = {AlquilerVehiculoConsumerException.class},
            attempts = "3",
            backoff = @Backoff(delay = 3000, multiplier = 2),
            retryTopicSuffix = "-retry",
            dltTopicSuffix = "-dlt",
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE
    )
    @KafkaListener(
            topics = "${custom.topic.alquiler.registro}",
            groupId = "${custom.group.alquiler}"
    )
    public void readMessage(
            String message,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset
    ) throws Exception {

        log.info("Mensaje recibido. topic={}, partition={}, offset={}, message={}",
                topic, partition, offset, message);

        try {
            AlquilerMessage alquilerMessage =
                    objectMapper.readValue(message, AlquilerMessage.class);

            if (alquilerMessage.getEstadoAlquiler() == null) {
                throw new AlquilerVehiculoConsumerException("El estado del alquiler es obligatorio");
            }

            vehiculoAlquilerService.validarDisponibilidadParaAlquiler(alquilerMessage);

        } catch (AlquilerVehiculoConsumerException e) {
            throw e;
        } catch (Exception e) {
            throw new AlquilerVehiculoConsumerException("Error procesando mensaje de alquiler", e);
        }
    }

    @DltHandler
    public void dltHandler(
            String message,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset,
            @Header(KafkaHeaders.EXCEPTION_MESSAGE) String errorMessage
    ) {
        log.error("Mensaje enviado a DLT. topic={}, partition={}, offset={}, error={}, message={}",
                topic, partition, offset, errorMessage, message);
    }
}
