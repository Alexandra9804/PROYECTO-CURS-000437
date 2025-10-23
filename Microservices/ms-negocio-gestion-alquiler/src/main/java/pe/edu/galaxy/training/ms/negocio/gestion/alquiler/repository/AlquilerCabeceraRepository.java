package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity.AlquilerCabeceraEntity;

@Repository
public interface AlquilerCabeceraRepository extends JpaRepository<AlquilerCabeceraEntity, Long> {
}
