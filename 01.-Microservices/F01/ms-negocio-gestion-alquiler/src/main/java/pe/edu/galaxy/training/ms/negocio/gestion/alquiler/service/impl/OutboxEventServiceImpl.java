package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity.OutboxEventAttemptEntity;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity.OutboxEventEntity;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.enums.OutboxStatus;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.repository.OutboxEventAttemptRepository;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.repository.OutboxEventRepository;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.OutboxEventService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OutboxEventServiceImpl implements OutboxEventService {

    private final OutboxEventRepository outboxEventRepository;
    private final OutboxEventAttemptRepository outboxEventAttemptRepository;

    @Override
    @Transactional
    public void markAsPublished(Long aggregateId) {
        outboxEventRepository.findFirstByAggregateIdAndStatusNotOrderByCreatedAtDesc(
                        aggregateId,
                        OutboxStatus.PUBLICADO
                )
                .ifPresent(outbox -> {

                    int nextRetryCount = outbox.getFailedAttemptCount() + 1;

                    outbox.setStatus(OutboxStatus.PUBLICADO);
                    outbox.setPublishedAt(LocalDateTime.now());
                    outbox.setErrorMessage(null);
                    outboxEventRepository.save(outbox);

                    registrarIntento(outbox, nextRetryCount, OutboxStatus.PUBLICADO.name(), null);

                });
    }

    @Override
    @Transactional
    public void markAsError(Long aggregateId, String errorMessage) {
        outboxEventRepository.findFirstByAggregateIdAndStatusNotOrderByCreatedAtDesc(
                        aggregateId,
                        OutboxStatus.PUBLICADO
                )
                .ifPresent(outbox -> {
                    int nextRetryCount = outbox.getFailedAttemptCount() + 1;

                    outbox.setStatus(OutboxStatus.ERROR);
                    outbox.setFailedAttemptCount(nextRetryCount);
                    outbox.setErrorMessage(errorMessage);

                    outboxEventRepository.save(outbox);

                    registrarIntento(outbox, nextRetryCount, OutboxStatus.ERROR.name(), errorMessage);
                });
    }

    private void registrarIntento(OutboxEventEntity outbox, Integer attemptNumber, String status, String errorMessage) {
        OutboxEventAttemptEntity attempt = OutboxEventAttemptEntity.builder()
                .idOutbox(outbox.getIdOutbox())
                .attemptNumber(attemptNumber)
                .status(status)
                .errorMessage(errorMessage)
                .createdAt(LocalDateTime.now())
                .build();

        outboxEventAttemptRepository.save(attempt);
    }
}
