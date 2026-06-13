package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity.VehiculoAlquilerValidacionEntity;

import java.util.Optional;

public interface VehiculoAlquilerValidacionRepository
        extends JpaRepository<VehiculoAlquilerValidacionEntity, Long> {

    Optional<VehiculoAlquilerValidacionEntity> findByIdAlquiler(Long idAlquiler);
}