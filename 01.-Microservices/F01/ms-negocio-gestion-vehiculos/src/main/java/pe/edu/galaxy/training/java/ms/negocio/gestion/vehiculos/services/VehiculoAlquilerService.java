package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.services;

import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.message.AlquilerMessage;

public interface VehiculoAlquilerService {
    void validarDisponibilidadParaAlquiler(AlquilerMessage alquilerMessage);
}
