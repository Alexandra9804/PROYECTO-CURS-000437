package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.enums;

public enum EstadoAlquiler {
    PENDIENTE_VALIDACION,  // Se registró la solicitud y está esperando validación del vehículo.
    CONFIRMADO,            // El vehículo fue validado y reservado correctamente.
    RECHAZADO,             // No se pudo atender el alquiler, por ejemplo porque el vehículo no estaba disponible.
    EN_CURSO,              // El vehículo ya fue entregado al cliente y el alquiler está activo.
    FINALIZADO,            // El alquiler terminó y el vehículo fue devuelto.
    ANULADO                // El alquiler fue cancelado manualmente o anulado por algún motivo administrativo.
}


