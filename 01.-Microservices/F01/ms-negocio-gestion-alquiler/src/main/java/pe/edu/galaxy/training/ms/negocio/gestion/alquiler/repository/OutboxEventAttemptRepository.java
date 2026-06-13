package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity.OutboxEventAttemptEntity;

public interface OutboxEventAttemptRepository extends JpaRepository<OutboxEventAttemptEntity, Long> {
}