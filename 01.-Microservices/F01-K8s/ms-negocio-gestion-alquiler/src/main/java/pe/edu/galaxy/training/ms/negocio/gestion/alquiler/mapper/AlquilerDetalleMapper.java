package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto.AlquilerDetalleRequestDto;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto.AlquilerDetalleResponseDto;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity.AlquilerDetalleEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AlquilerDetalleMapper {
    AlquilerDetalleEntity toEntity(AlquilerDetalleRequestDto dto);

    AlquilerDetalleResponseDto toDto(AlquilerDetalleEntity entity);

}
