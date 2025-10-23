package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.VehiculoRequestDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.VehiculoResponseDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity.VehiculoEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VehiculoMapper {

    VehiculoEntity toEntity(VehiculoRequestDto dto);

    @Mapping(source = "modeloEntity.idModelo", target = "idModelo")
    @Mapping(source = "modeloEntity.nombre", target = "modeloNombre")
    @Mapping(source = "modeloEntity.marcaEntity.idMarca", target = "idMarca")
    @Mapping(source = "modeloEntity.marcaEntity.nombre", target = "marcaNombre")
    VehiculoResponseDto toDTO(VehiculoEntity entity);
}
