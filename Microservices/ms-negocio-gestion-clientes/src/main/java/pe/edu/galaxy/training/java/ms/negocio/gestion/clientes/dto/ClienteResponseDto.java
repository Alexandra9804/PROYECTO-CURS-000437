package pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "ClienteResponseDTO",
        description = "Representa la información completa del cliente y sus datos personales."
)
public class ClienteResponseDto {
    @Schema(
            description = "Identificador único del cliente",
            example = "1001"
    )
    private Long idCliente;

    @Schema(
            description = "Descripción del tipo de cliente (por ejemplo: Regular, Corporativo, Premium)",
            example = "Regular"
    )
    private String tipoClienteDescripcion;

    @Schema(
            description = "Nombre de la persona",
            example = "Juan"
    )
    private String nombres;

    @Schema(
            description = "Apellido paterno",
            example = "Perez"
    )
    private String apellidoPaterno;

    @Schema(
            description = "Apellido materno",
            example = "Diaz"
    )
    private String apellidoMaterno;

    @Schema(
            description = "Razón social (solo si es persona jurídica)",
            example = "Empresa Los Andes S.A.C."
    )
    private String razonSocial;

    @Schema(
            description = "Tipo de persona (Natural o Jurídica)",
            example = "Natural"
    )
    private String tipoPersonaDescripcion;

    @Schema(
            description = "Tipo de documento del cliente",
            example = "DNI"
    )
    private String tipoDocumentoDescripcion;

    @Schema(
            description = "Número de documento de identidad o RUC del cliente",
            example = "12345678"
    )
    private String numeroDocumento;

    @Schema(
            description = "Dirección de residencia o domicilio fiscal",
            example = "Av. Los Olivos 123, Lima"
    )
    private String direccion;

    @Schema(
            description = "Teléfono de contacto del cliente",
            example = "999888777"
    )
    private String telefono;

    @Schema(
            description = "Correo electrónico del cliente",
            example = "juan.perez@gmail.com"
    )
    private String correo;

    @Schema(
            description = "Fecha de registro del cliente",
            example = "2025-10-22T09:30:00"
    )
    private String fechaRegistro;

    @Schema(
            description = "Estado del cliente (1 = Activo, 0 = Inactivo)",
            example = "1"
    )
    private Integer estado;
}
