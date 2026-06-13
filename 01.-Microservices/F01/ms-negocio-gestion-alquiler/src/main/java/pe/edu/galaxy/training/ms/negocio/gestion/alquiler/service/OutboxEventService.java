package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service;

public interface OutboxEventService {
    void markAsPublished(Long aggregateId);

    void markAsError(Long aggregateId, String errorMessage);
}
