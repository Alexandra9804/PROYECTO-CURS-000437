package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto.AlquilerDetalleRequestDto;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto.AlquilerDetalleResponseDto;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity.AlquilerDetalleEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-23T17:05:40-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class AlquilerDetalleMapperImpl implements AlquilerDetalleMapper {

    @Override
    public AlquilerDetalleEntity toEntity(AlquilerDetalleRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        AlquilerDetalleEntity alquilerDetalleEntity = new AlquilerDetalleEntity();

        alquilerDetalleEntity.setIdVehiculo( dto.getIdVehiculo() );
        alquilerDetalleEntity.setCantidad( dto.getCantidad() );

        return alquilerDetalleEntity;
    }

    @Override
    public AlquilerDetalleResponseDto toDto(AlquilerDetalleEntity entity) {
        if ( entity == null ) {
            return null;
        }

        AlquilerDetalleResponseDto alquilerDetalleResponseDto = new AlquilerDetalleResponseDto();

        alquilerDetalleResponseDto.setIdAlquilerDetalle( entity.getIdAlquilerDetalle() );
        alquilerDetalleResponseDto.setIdVehiculo( entity.getIdVehiculo() );
        alquilerDetalleResponseDto.setPrecioUnitario( entity.getPrecioUnitario() );
        alquilerDetalleResponseDto.setCantidad( entity.getCantidad() );
        alquilerDetalleResponseDto.setSubTotal( entity.getSubTotal() );
        alquilerDetalleResponseDto.setIgv( entity.getIgv() );
        alquilerDetalleResponseDto.setTotal( entity.getTotal() );

        return alquilerDetalleResponseDto;
    }
}
