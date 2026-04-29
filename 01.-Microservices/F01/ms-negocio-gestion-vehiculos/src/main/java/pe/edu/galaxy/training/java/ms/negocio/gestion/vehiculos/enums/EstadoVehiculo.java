package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.enums;

public enum EstadoVehiculo {
    DISPONIBLE,     // Puede ser alquilado
    RESERVADO,      //Ya fue separado para un alquiler, pero todavía no necesariamente entregado.
    ALQUILADO,      // Actualmente en uso
    MANTENIMIENTO,  // En revisión o reparación
    INACTIVO        // Dado de baja temporal o definitiva
}
