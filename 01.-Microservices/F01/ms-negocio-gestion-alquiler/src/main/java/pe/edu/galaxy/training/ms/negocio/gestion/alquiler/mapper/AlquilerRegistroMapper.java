package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto.AlquilerRegistroDto;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto.AlquilerRegistroRequestDto;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto.AlquilerRegistroResponseDto;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity.AlquilerEntity;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.events.AlquilerEvent;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AlquilerRegistroMapper {

    AlquilerRegistroDto toDto(AlquilerRegistroRequestDto alquilerRegistroRequestDto);

    AlquilerEntity toEntity(AlquilerRegistroDto dto);

    @Mapping(target = "estadoAlquiler", source = "estadoAlquiler.descripcion")
    AlquilerRegistroResponseDto toResponseDto(AlquilerEntity entity);

    @Mapping(target = "estadoAlquiler", source = "estadoAlquiler.descripcion")
    AlquilerEvent toEvent(AlquilerEntity entity);
}
