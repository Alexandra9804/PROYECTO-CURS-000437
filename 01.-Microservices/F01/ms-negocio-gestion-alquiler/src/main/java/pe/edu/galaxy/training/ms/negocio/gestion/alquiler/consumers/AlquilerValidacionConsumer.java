package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.consumers.message.AlquilerValidacionMessage;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.AlquilerValidacionService;

@Slf4j
@Component
@RequiredArgsConstructor
public class AlquilerValidacionConsumer {
    private final ObjectMapper objectMapper;
    private final AlquilerValidacionService alquilerValidacionService;

    @KafkaListener(
            topics = "${custom.topic.alquiler.validacion}",
            groupId = "${custom.group.alquiler-validacion}"
    )
    public void readMessage(String message) throws JsonProcessingException {

        log.info("Mensaje recibido desde Kafka: {}", message);

        AlquilerValidacionMessage validacionMessage =
                objectMapper.readValue(message, AlquilerValidacionMessage.class);

        log.info("Mensaje convertido: {}", validacionMessage);

        alquilerValidacionService.actualizarEstadoAlquiler(validacionMessage);
    }
}
