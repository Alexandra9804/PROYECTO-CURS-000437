package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.VehiculoRequestDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.VehiculoResponseDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity.MarcaEntity;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity.ModeloEntity;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity.VehiculoEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-23T16:35:16-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class VehiculoMapperImpl implements VehiculoMapper {

    @Override
    public VehiculoEntity toEntity(VehiculoRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        VehiculoEntity vehiculoEntity = new VehiculoEntity();

        vehiculoEntity.setPlaca( dto.getPlaca() );
        vehiculoEntity.setAnio( dto.getAnio() );
        vehiculoEntity.setColor( dto.getColor() );
        vehiculoEntity.setKilometraje( dto.getKilometraje() );
        vehiculoEntity.setVin( dto.getVin() );
        vehiculoEntity.setTipoCombustible( dto.getTipoCombustible() );
        vehiculoEntity.setTransmision( dto.getTransmision() );
        vehiculoEntity.setEstadoVehiculo( dto.getEstadoVehiculo() );

        return vehiculoEntity;
    }

    @Override
    public VehiculoResponseDto toDTO(VehiculoEntity entity) {
        if ( entity == null ) {
            return null;
        }

        VehiculoResponseDto vehiculoResponseDto = new VehiculoResponseDto();

        vehiculoResponseDto.setIdModelo( entityModeloEntityIdModelo( entity ) );
        vehiculoResponseDto.setModeloNombre( entityModeloEntityNombre( entity ) );
        vehiculoResponseDto.setIdMarca( entityModeloEntityMarcaEntityIdMarca( entity ) );
        vehiculoResponseDto.setMarcaNombre( entityModeloEntityMarcaEntityNombre( entity ) );
        vehiculoResponseDto.setIdVehiculo( entity.getIdVehiculo() );
        vehiculoResponseDto.setPlaca( entity.getPlaca() );
        vehiculoResponseDto.setAnio( entity.getAnio() );
        vehiculoResponseDto.setColor( entity.getColor() );
        vehiculoResponseDto.setKilometraje( entity.getKilometraje() );
        vehiculoResponseDto.setVin( entity.getVin() );
        vehiculoResponseDto.setTipoCombustible( entity.getTipoCombustible() );
        vehiculoResponseDto.setTransmision( entity.getTransmision() );
        vehiculoResponseDto.setEstadoVehiculo( entity.getEstadoVehiculo() );

        return vehiculoResponseDto;
    }

    private Long entityModeloEntityIdModelo(VehiculoEntity vehiculoEntity) {
        if ( vehiculoEntity == null ) {
            return null;
        }
        ModeloEntity modeloEntity = vehiculoEntity.getModeloEntity();
        if ( modeloEntity == null ) {
            return null;
        }
        Long idModelo = modeloEntity.getIdModelo();
        if ( idModelo == null ) {
            return null;
        }
        return idModelo;
    }

    private String entityModeloEntityNombre(VehiculoEntity vehiculoEntity) {
        if ( vehiculoEntity == null ) {
            return null;
        }
        ModeloEntity modeloEntity = vehiculoEntity.getModeloEntity();
        if ( modeloEntity == null ) {
            return null;
        }
        String nombre = modeloEntity.getNombre();
        if ( nombre == null ) {
            return null;
        }
        return nombre;
    }

    private Long entityModeloEntityMarcaEntityIdMarca(VehiculoEntity vehiculoEntity) {
        if ( vehiculoEntity == null ) {
            return null;
        }
        ModeloEntity modeloEntity = vehiculoEntity.getModeloEntity();
        if ( modeloEntity == null ) {
            return null;
        }
        MarcaEntity marcaEntity = modeloEntity.getMarcaEntity();
        if ( marcaEntity == null ) {
            return null;
        }
        Long idMarca = marcaEntity.getIdMarca();
        if ( idMarca == null ) {
            return null;
        }
        return idMarca;
    }

    private String entityModeloEntityMarcaEntityNombre(VehiculoEntity vehiculoEntity) {
        if ( vehiculoEntity == null ) {
            return null;
        }
        ModeloEntity modeloEntity = vehiculoEntity.getModeloEntity();
        if ( modeloEntity == null ) {
            return null;
        }
        MarcaEntity marcaEntity = modeloEntity.getMarcaEntity();
        if ( marcaEntity == null ) {
            return null;
        }
        String nombre = marcaEntity.getNombre();
        if ( nombre == null ) {
            return null;
        }
        return nombre;
    }
}
