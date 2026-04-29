package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto.AlquilerRegistroRequestDto;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto.AlquilerRegistroResponseDto;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity.AlquilerEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AlquilerRegistroMapper {
    AlquilerEntity toEntity(AlquilerRegistroRequestDto dto);

    @Mapping(target = "estadoAlquiler", source = "estadoAlquiler.descripcion")
    AlquilerRegistroResponseDto toDto(AlquilerEntity entity);
}
