package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.exceptions;

public class AlquilerValidacionConsumerException extends RuntimeException {

    public AlquilerValidacionConsumerException(String message) {
        super(message);
    }

    public AlquilerValidacionConsumerException(String message, Throwable cause) {
        super(message, cause);
    }
}