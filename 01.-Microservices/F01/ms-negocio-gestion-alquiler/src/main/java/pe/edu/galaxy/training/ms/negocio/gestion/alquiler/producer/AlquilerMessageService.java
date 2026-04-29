package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.producer;

public interface AlquilerMessageService {
    void sendMessage(AlquilerMessage alquilerMessage) throws MessageException;
}
