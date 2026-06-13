package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity.OutboxEventEntity;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.enums.OutboxStatus;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.repository.OutboxEventRepository;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.OutboxEventService;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxPublisherScheduler {

    private final OutboxEventRepository outboxEventRepository;
    private final KafkaProducer<String, String> kafkaProducer;
    private final OutboxEventService outboxEventService;

    @Scheduled(fixedDelay = 60000)
    public void publishPendingEvents() {

        List<OutboxEventEntity> events = outboxEventRepository
                .findTop20ByStatusInOrderByCreatedAtAsc(
                        List.of(OutboxStatus.PENDIENTE, OutboxStatus.ERROR)
                );

        for (OutboxEventEntity event : events) {

            ProducerRecord<String, String> record = new ProducerRecord<>(
                    event.getTopic(),
                    event.getMessageKey(),
                    event.getPayload()
            );

            kafkaProducer.send(record, (metadata, exception) -> {
                if (exception != null) {

                    outboxEventService.markAsError(
                            event.getAggregateId(),
                            exception.getMessage()
                    );

                    log.error("Error reprocesando outbox. idOutbox={}, aggregateId={}, error={}",
                            event.getIdOutbox(),
                            event.getAggregateId(),
                            exception.getMessage());

                    return;
                }

                outboxEventService.markAsPublished(event.getAggregateId());

                log.info("Outbox reprocesado OK. idOutbox={}, aggregateId={}, topic={}, offset={}",
                        event.getIdOutbox(),
                        event.getAggregateId(),
                        metadata.topic(),
                        metadata.offset());
            });
        }
    }
}