package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.events.AlquilerEvent;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.mapper.AlquilerMessageMapper;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.producer.AlquilerMessage;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.producer.AlquilerMessageService;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.producer.MessageException;

@Slf4j
@RequiredArgsConstructor
@Component
public class AlquilerListener {

    private final AlquilerMessageMapper alquilerMessageMapper;
    private final AlquilerMessageService alquilerMessageService;

    @EventListener
    public void handler(AlquilerEvent alquilerEvent) throws MessageException {
        log.info("AlquilerEvent recibido => {}", alquilerEvent);

        AlquilerMessage alquilerMessage = alquilerMessageMapper.toMessage(alquilerEvent);

        log.info("AlquilerMessage generado => {}", alquilerMessage);

        alquilerMessageService.sendMessage(alquilerMessage);
    }
}
