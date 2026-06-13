package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.events;

import java.time.LocalDate;

public record AlquilerEvent(
        Long idAlquiler,
        Long idCliente,
        Long idVehiculo,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        String estadoAlquiler,
        Double total
) {
}
