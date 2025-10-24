package pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.dto.ClienteRequestDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.dto.ClienteResponseDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.dto.PersonaRequestDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.entity.ClienteEntity;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.entity.PersonaEntity;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.entity.TipoClienteEntity;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.entity.TipoDocumentoEntity;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.entity.TipoPersonaEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-23T16:47:19-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteEntity toEntity(ClienteRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setTipoCliente( clienteRequestDtoToTipoClienteEntity( dto ) );
        clienteEntity.setPersona( personaRequestDtoToPersonaEntity( dto.getPersona() ) );

        return clienteEntity;
    }

    @Override
    public ClienteResponseDto toDTO(ClienteEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ClienteResponseDto clienteResponseDto = new ClienteResponseDto();

        clienteResponseDto.setTipoClienteDescripcion( entityTipoClienteDescripcion( entity ) );
        clienteResponseDto.setTipoPersonaDescripcion( entityPersonaTipoPersonaDescripcion( entity ) );
        clienteResponseDto.setTipoDocumentoDescripcion( entityPersonaTipoDocumentoDescripcion( entity ) );
        clienteResponseDto.setNombres( entityPersonaNombres( entity ) );
        clienteResponseDto.setApellidoPaterno( entityPersonaApellidoPaterno( entity ) );
        clienteResponseDto.setApellidoMaterno( entityPersonaApellidoMaterno( entity ) );
        clienteResponseDto.setRazonSocial( entityPersonaRazonSocial( entity ) );
        clienteResponseDto.setNumeroDocumento( entityPersonaNumeroDocumento( entity ) );
        clienteResponseDto.setDireccion( entityPersonaDireccion( entity ) );
        clienteResponseDto.setTelefono( entityPersonaTelefono( entity ) );
        clienteResponseDto.setCorreo( entityPersonaCorreo( entity ) );
        clienteResponseDto.setIdCliente( entity.getIdCliente() );
        if ( entity.getEstado() != null ) {
            clienteResponseDto.setEstado( Integer.parseInt( entity.getEstado() ) );
        }

        return clienteResponseDto;
    }

    protected TipoClienteEntity clienteRequestDtoToTipoClienteEntity(ClienteRequestDto clienteRequestDto) {
        if ( clienteRequestDto == null ) {
            return null;
        }

        TipoClienteEntity tipoClienteEntity = new TipoClienteEntity();

        tipoClienteEntity.setIdTipoCliente( clienteRequestDto.getTipoClienteId() );

        return tipoClienteEntity;
    }

    protected TipoPersonaEntity personaRequestDtoToTipoPersonaEntity(PersonaRequestDto personaRequestDto) {
        if ( personaRequestDto == null ) {
            return null;
        }

        TipoPersonaEntity tipoPersonaEntity = new TipoPersonaEntity();

        tipoPersonaEntity.setIdTipoPersona( personaRequestDto.getTipoPersona() );

        return tipoPersonaEntity;
    }

    protected TipoDocumentoEntity personaRequestDtoToTipoDocumentoEntity(PersonaRequestDto personaRequestDto) {
        if ( personaRequestDto == null ) {
            return null;
        }

        TipoDocumentoEntity tipoDocumentoEntity = new TipoDocumentoEntity();

        tipoDocumentoEntity.setIdTipoDocumento( personaRequestDto.getTipoDocumento() );

        return tipoDocumentoEntity;
    }

    protected PersonaEntity personaRequestDtoToPersonaEntity(PersonaRequestDto personaRequestDto) {
        if ( personaRequestDto == null ) {
            return null;
        }

        PersonaEntity personaEntity = new PersonaEntity();

        personaEntity.setTipoPersona( personaRequestDtoToTipoPersonaEntity( personaRequestDto ) );
        personaEntity.setTipoDocumento( personaRequestDtoToTipoDocumentoEntity( personaRequestDto ) );
        personaEntity.setNombres( personaRequestDto.getNombres() );
        personaEntity.setApellidoPaterno( personaRequestDto.getApellidoPaterno() );
        personaEntity.setApellidoMaterno( personaRequestDto.getApellidoMaterno() );
        personaEntity.setRazonSocial( personaRequestDto.getRazonSocial() );
        personaEntity.setNumeroDocumento( personaRequestDto.getNumeroDocumento() );
        personaEntity.setDireccion( personaRequestDto.getDireccion() );
        personaEntity.setTelefono( personaRequestDto.getTelefono() );
        personaEntity.setCorreo( personaRequestDto.getCorreo() );

        return personaEntity;
    }

    private String entityTipoClienteDescripcion(ClienteEntity clienteEntity) {
        if ( clienteEntity == null ) {
            return null;
        }
        TipoClienteEntity tipoCliente = clienteEntity.getTipoCliente();
        if ( tipoCliente == null ) {
            return null;
        }
        String descripcion = tipoCliente.getDescripcion();
        if ( descripcion == null ) {
            return null;
        }
        return descripcion;
    }

    private String entityPersonaTipoPersonaDescripcion(ClienteEntity clienteEntity) {
        if ( clienteEntity == null ) {
            return null;
        }
        PersonaEntity persona = clienteEntity.getPersona();
        if ( persona == null ) {
            return null;
        }
        TipoPersonaEntity tipoPersona = persona.getTipoPersona();
        if ( tipoPersona == null ) {
            return null;
        }
        String descripcion = tipoPersona.getDescripcion();
        if ( descripcion == null ) {
            return null;
        }
        return descripcion;
    }

    private String entityPersonaTipoDocumentoDescripcion(ClienteEntity clienteEntity) {
        if ( clienteEntity == null ) {
            return null;
        }
        PersonaEntity persona = clienteEntity.getPersona();
        if ( persona == null ) {
            return null;
        }
        TipoDocumentoEntity tipoDocumento = persona.getTipoDocumento();
        if ( tipoDocumento == null ) {
            return null;
        }
        String descripcion = tipoDocumento.getDescripcion();
        if ( descripcion == null ) {
            return null;
        }
        return descripcion;
    }

    private String entityPersonaNombres(ClienteEntity clienteEntity) {
        if ( clienteEntity == null ) {
            return null;
        }
        PersonaEntity persona = clienteEntity.getPersona();
        if ( persona == null ) {
            return null;
        }
        String nombres = persona.getNombres();
        if ( nombres == null ) {
            return null;
        }
        return nombres;
    }

    private String entityPersonaApellidoPaterno(ClienteEntity clienteEntity) {
        if ( clienteEntity == null ) {
            return null;
        }
        PersonaEntity persona = clienteEntity.getPersona();
        if ( persona == null ) {
            return null;
        }
        String apellidoPaterno = persona.getApellidoPaterno();
        if ( apellidoPaterno == null ) {
            return null;
        }
        return apellidoPaterno;
    }

    private String entityPersonaApellidoMaterno(ClienteEntity clienteEntity) {
        if ( clienteEntity == null ) {
            return null;
        }
        PersonaEntity persona = clienteEntity.getPersona();
        if ( persona == null ) {
            return null;
        }
        String apellidoMaterno = persona.getApellidoMaterno();
        if ( apellidoMaterno == null ) {
            return null;
        }
        return apellidoMaterno;
    }

    private String entityPersonaRazonSocial(ClienteEntity clienteEntity) {
        if ( clienteEntity == null ) {
            return null;
        }
        PersonaEntity persona = clienteEntity.getPersona();
        if ( persona == null ) {
            return null;
        }
        String razonSocial = persona.getRazonSocial();
        if ( razonSocial == null ) {
            return null;
        }
        return razonSocial;
    }

    private String entityPersonaNumeroDocumento(ClienteEntity clienteEntity) {
        if ( clienteEntity == null ) {
            return null;
        }
        PersonaEntity persona = clienteEntity.getPersona();
        if ( persona == null ) {
            return null;
        }
        String numeroDocumento = persona.getNumeroDocumento();
        if ( numeroDocumento == null ) {
            return null;
        }
        return numeroDocumento;
    }

    private String entityPersonaDireccion(ClienteEntity clienteEntity) {
        if ( clienteEntity == null ) {
            return null;
        }
        PersonaEntity persona = clienteEntity.getPersona();
        if ( persona == null ) {
            return null;
        }
        String direccion = persona.getDireccion();
        if ( direccion == null ) {
            return null;
        }
        return direccion;
    }

    private String entityPersonaTelefono(ClienteEntity clienteEntity) {
        if ( clienteEntity == null ) {
            return null;
        }
        PersonaEntity persona = clienteEntity.getPersona();
        if ( persona == null ) {
            return null;
        }
        String telefono = persona.getTelefono();
        if ( telefono == null ) {
            return null;
        }
        return telefono;
    }

    private String entityPersonaCorreo(ClienteEntity clienteEntity) {
        if ( clienteEntity == null ) {
            return null;
        }
        PersonaEntity persona = clienteEntity.getPersona();
        if ( persona == null ) {
            return null;
        }
        String correo = persona.getCorreo();
        if ( correo == null ) {
            return null;
        }
        return correo;
    }
}
