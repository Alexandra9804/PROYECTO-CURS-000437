package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
