package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity.VehiculoEntity;

@Repository
public interface VehiculoRepository extends JpaRepository<VehiculoEntity, Long> {

}
