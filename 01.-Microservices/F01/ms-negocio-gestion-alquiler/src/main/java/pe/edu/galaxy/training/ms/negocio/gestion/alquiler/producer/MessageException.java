package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.producer;

public class MessageException extends Exception{
    public MessageException(Throwable cause) {
        super(cause);
    }

    public MessageException(String message) {
        super(message);
    }
}
