package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service;

import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.consumers.message.AlquilerValidacionMessage;

public interface AlquilerValidacionService {
    void actualizarEstadoAlquiler(AlquilerValidacionMessage message);
}
