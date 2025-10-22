package pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.dto.ClienteRequestDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.dto.ClienteResponseDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.entity.ClienteEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClienteMapper {
    @Mapping(source = "tipoClienteId", target = "tipoCliente.idTipoCliente")
    @Mapping(source = "persona.tipoPersona", target = "persona.tipoPersona.idTipoPersona")
    @Mapping(source = "persona.tipoDocumento", target = "persona.tipoDocumento.idTipoDocumento")
    ClienteEntity toEntity(ClienteRequestDto dto);

    @Mapping(source = "tipoCliente.descripcion", target = "tipoClienteDescripcion")
    @Mapping(source = "persona.tipoPersona.descripcion", target = "tipoPersonaDescripcion")
    @Mapping(source = "persona.tipoDocumento.descripcion", target = "tipoDocumentoDescripcion")
    @Mapping(source = "persona.nombres", target = "nombres")
    @Mapping(source = "persona.apellidoPaterno", target = "apellidoPaterno")
    @Mapping(source = "persona.apellidoMaterno", target = "apellidoMaterno")
    @Mapping(source = "persona.razonSocial", target = "razonSocial")
    @Mapping(source = "persona.numeroDocumento", target = "numeroDocumento")
    @Mapping(source = "persona.direccion", target = "direccion")
    @Mapping(source = "persona.telefono", target = "telefono")
    @Mapping(source = "persona.correo", target = "correo")
    ClienteResponseDto toDTO(ClienteEntity entity);
}
