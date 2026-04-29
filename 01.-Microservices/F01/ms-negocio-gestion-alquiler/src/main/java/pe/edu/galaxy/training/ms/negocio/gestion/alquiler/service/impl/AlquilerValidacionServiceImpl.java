package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.consumers.message.AlquilerValidacionMessage;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity.AlquilerEntity;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.repository.AlquilerRepository;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.AlquilerValidacionService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlquilerValidacionServiceImpl implements AlquilerValidacionService {

    private final AlquilerRepository alquilerRepository;

    @Override
    public void actualizarEstadoAlquiler(AlquilerValidacionMessage message) {

        AlquilerEntity alquiler = alquilerRepository.findById(message.getIdAlquiler())
                .orElseThrow(() -> new RuntimeException(
                        "No se encontró el alquiler con ID " + message.getIdAlquiler()
                ));

        alquiler.setIdAlquilerEstado(message.getIdAlquilerEstado());

        alquilerRepository.save(alquiler);

        log.info("Estado de alquiler actualizado. idAlquiler={}, idEstado={}, mensaje={}",
                message.getIdAlquiler(),
                message.getIdAlquilerEstado(),
                message.getMensaje());
    }
}
