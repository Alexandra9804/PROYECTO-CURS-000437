package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.producer.AlquilerMessage;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.producer.AlquilerMessageService;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.producer.MessageException;

@Slf4j
@RequiredArgsConstructor
@Component
public class AlquilerListener {

    private final AlquilerMessageService alquilerMessageService;

    @EventListener
    public void handler(AlquilerMessage usuarioMessage) throws MessageException {
        log.info("UsuarioListener::handler => {}",usuarioMessage);
        alquilerMessageService.sendMessage(usuarioMessage);
    }
}
