package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.producer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlquilerMessage {
    private Long idAlquiler;
    private Long idCliente;
    private Long idVehiculo;
    private String fechaInicio;
    private String fechaFin;
    private String estadoAlquiler;
    private Double total;
    private String fecha;
}
