package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity.VehiculoEntity;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.enums.EstadoVehiculo;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.message.AlquilerMessage;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.producer.AlquilerValidacionMessage;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.producer.VehiculoAlquilerMessageService;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.repository.VehiculoRepository;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.services.VehiculoAlquilerService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class VehiculoAlquilerServiceImpl implements VehiculoAlquilerService {

    private static final Long ESTADO_ALQUILER_CONFIRMADO = 2L;
    private static final Long ESTADO_ALQUILER_RECHAZADO = 3L;
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final VehiculoRepository vehiculoRepository;
    private final VehiculoAlquilerMessageService vehiculoAlquilerMessageService;

    @Override
    public void validarDisponibilidadParaAlquiler(AlquilerMessage alquilerMessage) {

        VehiculoEntity vehiculo = vehiculoRepository.findById(alquilerMessage.getIdVehiculo())
                .orElseThrow(() -> new RuntimeException(
                        "No se encontró el vehículo con ID " + alquilerMessage.getIdVehiculo()
                ));

        EstadoVehiculo estadoActual = vehiculo.getEstadoVehiculo();

        if (estadoActual != EstadoVehiculo.DISPONIBLE) {
            AlquilerValidacionMessage rechazado = AlquilerValidacionMessage.builder()
                    .idAlquiler(alquilerMessage.getIdAlquiler())
                    .idVehiculo(alquilerMessage.getIdVehiculo())
                    .idAlquilerEstado(ESTADO_ALQUILER_RECHAZADO)
                    .resultado("RECHAZADO")
                    .mensaje("Vehículo no disponible. Estado actual: " + estadoActual)
                    .fecha(LocalDateTime.now().format(DATE_TIME_FORMATTER))
                    .build();

            vehiculoAlquilerMessageService.publicarValidacion(rechazado);

            log.warn("Vehículo no disponible. idVehiculo={}, estadoActual={}, idAlquiler={}",
                    vehiculo.getIdVehiculo(),
                    estadoActual,
                    alquilerMessage.getIdAlquiler());

            return;
        }

        vehiculo.setEstadoVehiculo(EstadoVehiculo.RESERVADO);
        vehiculoRepository.save(vehiculo);

        AlquilerValidacionMessage confirmado = AlquilerValidacionMessage.builder()
                .idAlquiler(alquilerMessage.getIdAlquiler())
                .idVehiculo(alquilerMessage.getIdVehiculo())
                .idAlquilerEstado(ESTADO_ALQUILER_CONFIRMADO)
                .resultado("CONFIRMADO")
                .mensaje("Vehículo reservado correctamente")
                .fecha(LocalDateTime.now().format(DATE_TIME_FORMATTER))
                .build();

        vehiculoAlquilerMessageService.publicarValidacion(confirmado);

        log.info("Vehículo reservado correctamente. idVehiculo={}, idAlquiler={}",
                vehiculo.getIdVehiculo(),
                alquilerMessage.getIdAlquiler());
    }

}