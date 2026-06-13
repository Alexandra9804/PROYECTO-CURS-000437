package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.consumers.message.AlquilerValidacionMessage;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity.AlquilerEntity;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.mail.AlquilerMailBuilder;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.mail.MailService;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.repository.AlquilerRepository;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.AlquilerValidacionService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlquilerValidacionServiceImpl implements AlquilerValidacionService {

    private static final Long ESTADO_ALQUILER_CONFIRMADO = 2L;

    private final AlquilerRepository alquilerRepository;
    private final MailService mailService;
    private final AlquilerMailBuilder alquilerMailBuilder;

    @Override
    @Transactional
    public void actualizarEstadoAlquiler(AlquilerValidacionMessage message) {

        AlquilerEntity alquiler = alquilerRepository.findById(message.getIdAlquiler())
                .orElseThrow(() -> new RuntimeException(
                        "No se encontró el alquiler con ID " + message.getIdAlquiler()
                ));

        if (alquiler.getIdAlquilerEstado().equals(message.getIdAlquilerEstado())) {
            log.info(
                    "Mensaje duplicado ignorado. El alquiler ya tiene ese estado. idAlquiler={}, idEstado={}, mensaje={}",
                    message.getIdAlquiler(),
                    message.getIdAlquilerEstado(),
                    message.getMensaje()
            );
            return;
        }

        alquiler.setIdAlquilerEstado(message.getIdAlquilerEstado());
        alquilerRepository.save(alquiler);

        if (ESTADO_ALQUILER_CONFIRMADO.equals(message.getIdAlquilerEstado())) {
            mailService.sendMail(
                    "kevinjoussef@gmail.com",
                    "Alquiler confirmado",
                    alquilerMailBuilder.buildResultadoAlquilerMessage(message)
            );
        }

        log.info("Estado de alquiler actualizado. idAlquiler={}, idEstado={}, mensaje={}",
                message.getIdAlquiler(),
                message.getIdAlquilerEstado(),
                message.getMensaje());
    }
}