package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity.AlquilerEstadoEntity;

public interface AlquilerEstadoRepository extends JpaRepository<AlquilerEstadoEntity, Long> {
}
