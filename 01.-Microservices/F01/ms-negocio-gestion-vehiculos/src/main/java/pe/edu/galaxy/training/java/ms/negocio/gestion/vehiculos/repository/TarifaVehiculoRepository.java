package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity.TarifaVehiculoEntity;

import java.util.Optional;

@Repository
public interface TarifaVehiculoRepository extends JpaRepository<TarifaVehiculoEntity, Long> {
    Optional<TarifaVehiculoEntity> findFirstByVehiculo_IdVehiculoAndFechaFinIsNullAndEstado(Long idVehiculo, String estado);
}
