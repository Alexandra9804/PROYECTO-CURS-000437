package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto.AlquilerResponseDto;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity.AlquilerEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AlquilerMapper {
    @Mapping(target = "estadoAlquiler", source = "estadoAlquiler.descripcion")
    @Mapping(target = "clienteResponseDto", ignore = true)
    @Mapping(target = "vehiculoResponseDto", ignore = true)
    AlquilerResponseDto toDto(AlquilerEntity entity);
}
