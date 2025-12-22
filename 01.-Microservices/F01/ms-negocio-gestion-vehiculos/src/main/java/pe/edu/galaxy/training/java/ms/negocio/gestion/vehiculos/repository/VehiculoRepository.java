package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity.VehiculoEntity;

@Repository
public interface VehiculoRepository extends JpaRepository<VehiculoEntity, Long> {
    @Query("""
    SELECT v
    FROM VehiculoEntity v
    WHERE (:vin IS NULL 
           OR LOWER(v.vin) LIKE LOWER(CONCAT('%', :vin, '%')))
      AND (:idMarca IS NULL 
           OR v.modeloEntity.marcaEntity.idMarca = :idMarca)
      AND (:idModelo IS NULL 
           OR v.modeloEntity.idModelo = :idModelo)
""")
    Page<VehiculoEntity> buscarVehiculos(
            @Param("vin") String vin,
            @Param("idMarca") Long idMarca,
            @Param("idModelo") Long idModelo,
            Pageable pageable
    );


}
