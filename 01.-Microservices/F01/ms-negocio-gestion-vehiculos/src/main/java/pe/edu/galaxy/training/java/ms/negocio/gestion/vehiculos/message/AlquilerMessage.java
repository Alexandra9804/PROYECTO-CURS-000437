package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.message;

import lombok.Data;

@Data
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
