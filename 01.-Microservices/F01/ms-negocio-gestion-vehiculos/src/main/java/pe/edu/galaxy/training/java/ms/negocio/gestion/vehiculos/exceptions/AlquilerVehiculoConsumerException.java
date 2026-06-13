package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.exceptions;

public class AlquilerVehiculoConsumerException extends RuntimeException{
    public AlquilerVehiculoConsumerException(String message) {
        super(message);
    }

    public AlquilerVehiculoConsumerException(String message, Throwable cause) {
        super(message, cause);
    }
}
