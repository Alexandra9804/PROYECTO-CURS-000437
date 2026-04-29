package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.producer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlquilerValidacionMessage {
    private Long idAlquiler;
    private Long idVehiculo;
    private Long idAlquilerEstado;
    private String resultado;
    private String mensaje;
    private String fecha;
}
