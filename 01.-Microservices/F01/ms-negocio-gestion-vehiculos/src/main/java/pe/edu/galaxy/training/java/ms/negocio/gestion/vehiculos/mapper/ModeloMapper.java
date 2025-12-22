package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.ModeloComboResponseDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.ModeloRequestDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.ModeloResponseDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity.ModeloEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ModeloMapper {

    ModeloEntity toEntity(ModeloRequestDto modeloRequestDto);

    @Mapping(target = "marcaId", source = "marcaEntity.idMarca")
    @Mapping(target = "marcaNombre", source = "marcaEntity.nombre")
    ModeloResponseDto toDTO(ModeloEntity modeloEntity);

    ModeloComboResponseDto toComboDTO(ModeloEntity modeloEntity);
}
