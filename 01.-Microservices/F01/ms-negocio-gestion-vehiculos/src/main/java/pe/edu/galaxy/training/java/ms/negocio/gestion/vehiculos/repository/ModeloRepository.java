package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.ModeloResponseDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity.ModeloEntity;

import java.util.List;


public interface ModeloRepository extends JpaRepository<ModeloEntity, Long> {
    @Query("""
    SELECT m FROM ModeloEntity m
    WHERE (:nombre IS NULL OR LOWER(m.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')))
      AND (:idMarca IS NULL OR m.marcaEntity.idMarca = :idMarca)
""")
    Page<ModeloEntity> buscarModelos(
            @Param("nombre") String nombre,
            @Param("idMarca") Long idMarca,
            Pageable pageable
    );

    List<ModeloEntity> findByMarcaEntity_IdMarca(Long idMarca);

}
