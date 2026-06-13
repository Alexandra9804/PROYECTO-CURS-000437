package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity.OutboxEventEntity;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.enums.OutboxStatus;

import java.util.List;
import java.util.Optional;

public interface OutboxEventRepository extends JpaRepository<OutboxEventEntity, Long> {

    List<OutboxEventEntity> findTop20ByStatusInOrderByCreatedAtAsc(List<OutboxStatus> statuses);
    Optional<OutboxEventEntity> findFirstByAggregateIdAndStatusNotOrderByCreatedAtDesc(
            Long aggregateId,
            OutboxStatus status
    );
}
