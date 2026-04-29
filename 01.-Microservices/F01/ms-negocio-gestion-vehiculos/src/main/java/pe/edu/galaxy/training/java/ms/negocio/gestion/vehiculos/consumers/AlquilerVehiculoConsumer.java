package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.message.AlquilerMessage;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.services.VehiculoAlquilerService;

@Slf4j
@RequiredArgsConstructor
@Component
public class AlquilerVehiculoConsumer {
    private final ObjectMapper objectMapper;
    private final VehiculoAlquilerService vehiculoAlquilerService;

    @KafkaListener(
            topics = {"${custom.topic.alquiler.registro}"},
            groupId = "${custom.group.alquiler}"
    )
    void readMessage(String message) throws JsonProcessingException {

        log.info("Read message from Kafka - String {}", message);

        AlquilerMessage alquilerMessage = objectMapper.readValue(message, AlquilerMessage.class);

        log.info("Read message from Kafka - AlquilerMessage {}", alquilerMessage);

        vehiculoAlquilerService.validarDisponibilidadParaAlquiler(alquilerMessage);
    }
}
