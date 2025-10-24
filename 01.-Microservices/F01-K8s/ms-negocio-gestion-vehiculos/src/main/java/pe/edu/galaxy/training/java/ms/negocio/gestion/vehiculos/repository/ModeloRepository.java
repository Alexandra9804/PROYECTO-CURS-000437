package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity.ModeloEntity;

public interface ModeloRepository extends JpaRepository<ModeloEntity, Long> {
}
