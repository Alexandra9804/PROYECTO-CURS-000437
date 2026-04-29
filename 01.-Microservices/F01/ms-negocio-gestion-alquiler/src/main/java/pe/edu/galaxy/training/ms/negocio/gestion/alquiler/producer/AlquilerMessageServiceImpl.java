package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AlquilerMessageServiceImpl implements AlquilerMessageService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${custom.topic.alquiler.registro}")
    private String topicAlquilerRegistro;

    @Override
    public void sendMessage(AlquilerMessage alquilerMessage) throws MessageException {
        try {
            log.info("AlquilerMessage => {}", alquilerMessage);

            String jsonMessage = objectMapper.writeValueAsString(alquilerMessage);

            log.info("jsonMessage => {}", jsonMessage);

            kafkaTemplate.send(
                    topicAlquilerRegistro,
                    String.valueOf(alquilerMessage.getIdAlquiler()),
                    jsonMessage
            );

            log.info("Mensaje publicado con éxito en topic {}", topicAlquilerRegistro);

        } catch (JsonProcessingException e) {
            throw new MessageException(e);
        }
    }
}