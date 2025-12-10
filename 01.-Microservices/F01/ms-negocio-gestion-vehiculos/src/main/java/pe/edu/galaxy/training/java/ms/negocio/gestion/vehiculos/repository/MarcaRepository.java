package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity.MarcaEntity;


public interface MarcaRepository extends JpaRepository<MarcaEntity, Long> {
    Page<MarcaEntity> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
