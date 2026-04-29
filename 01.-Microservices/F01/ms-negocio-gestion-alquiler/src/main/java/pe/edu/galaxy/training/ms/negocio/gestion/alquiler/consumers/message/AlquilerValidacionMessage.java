package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.consumers.message;

import lombok.Data;

@Data
public class AlquilerValidacionMessage {
    private Long idAlquiler;
    private Long idVehiculo;
    private Long idAlquilerEstado;
    private String mensaje;
    private String fecha;
}
