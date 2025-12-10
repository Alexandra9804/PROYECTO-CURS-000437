package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.MarcaRequestDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.MarcaResponseDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity.MarcaEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MarcaMapper {

    MarcaEntity toEntity(MarcaRequestDto marcaRequestDto);
    MarcaResponseDto toDTO(MarcaEntity marcaEntity);
}
