package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.producer;

public interface VehiculoAlquilerMessageService {
    void publicarValidacion(AlquilerValidacionMessage message);
}
