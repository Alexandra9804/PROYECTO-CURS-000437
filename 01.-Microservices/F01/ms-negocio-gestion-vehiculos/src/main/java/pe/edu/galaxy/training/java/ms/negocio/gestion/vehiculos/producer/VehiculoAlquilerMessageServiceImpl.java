package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class VehiculoAlquilerMessageServiceImpl implements VehiculoAlquilerMessageService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${custom.topic.alquiler.validacion}")
    private String topicAlquilerValidacion;

    @Override
    public void publicarValidacion(AlquilerValidacionMessage message) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(message);

            kafkaTemplate.send(
                    topicAlquilerValidacion,
                    String.valueOf(message.getIdAlquiler()),
                    jsonMessage
            );

            log.info("Mensaje publicado en Kafka. topic={}, message={}", topicAlquilerValidacion, jsonMessage);

        } catch (Exception e) {
            log.error("Error publicando validación de alquiler: {}", e.getMessage(), e);
        }
    }
}