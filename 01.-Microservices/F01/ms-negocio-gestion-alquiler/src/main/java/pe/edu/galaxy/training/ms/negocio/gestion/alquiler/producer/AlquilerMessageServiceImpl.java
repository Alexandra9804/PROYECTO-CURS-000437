package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.OutboxEventService;

@Slf4j
@RequiredArgsConstructor
@Component
public class AlquilerMessageServiceImpl implements AlquilerMessageService {

    private final KafkaProducer<String, String> kafkaProducer;
    private final ObjectMapper objectMapper;
    private final OutboxEventService outboxEventService;

    @Value("${custom.topic.alquiler.registro}")
    private String topicAlquilerRegistro;

    @Override
    public void sendMessage(AlquilerMessage alquilerMessage) throws MessageException {
        try {
            log.info("AlquilerMessage => {}", alquilerMessage);

            String jsonMessage = objectMapper.writeValueAsString(alquilerMessage);

            log.info("jsonMessage => {}", jsonMessage);

            ProducerRecord<String, String> record = new ProducerRecord<>(
                    topicAlquilerRegistro,
                    String.valueOf(alquilerMessage.getIdAlquiler()),
                    jsonMessage
            );

            kafkaProducer.send(record, buildCallback(alquilerMessage));

        } catch (JsonProcessingException e) {
            throw new MessageException("Error serializando mensaje de alquiler", e);
        } catch (Exception e) {
            throw new MessageException("Error publicando mensaje de alquiler", e);
        }
    }

    private Callback buildCallback(AlquilerMessage alquilerMessage) {
        return new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception != null) {
                    log.error(
                            "Error publicando alquiler en Kafka. topic={}, idAlquiler={}, error={}",
                            topicAlquilerRegistro,
                            alquilerMessage.getIdAlquiler(),
                            exception.getMessage(),
                            exception
                    );

                    outboxEventService.markAsError(
                            alquilerMessage.getIdAlquiler(),
                            exception.getMessage()
                    );
                    return;
                }

                log.info(
                        "Mensaje publicado correctamente. topic={}, partition={}, offset={}, idAlquiler={}",
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        alquilerMessage.getIdAlquiler()
                );

                outboxEventService.markAsPublished(alquilerMessage.getIdAlquiler());
            }
        };
    }
}