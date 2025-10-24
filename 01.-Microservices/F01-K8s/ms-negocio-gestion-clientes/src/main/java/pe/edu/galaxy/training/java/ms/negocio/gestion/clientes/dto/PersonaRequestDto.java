package pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonaRequestDto {

    @Schema(
            description = "Id del tipo de persona (1 = Natural, 2 = Jurídica)",
            example = "1"
    )
    private Long tipoPersona;

    @Schema(
            description = "Nombres completos de la persona",
            example = "Juan Carlos"
    )
    private String nombres;

    @Schema(
            description = "Apellido paterno de la persona",
            example = "Pérez"
    )
    private String apellidoPaterno;

    @Schema(
            description = "Apellido materno de la persona",
            example = "Gómez"
    )
    private String apellidoMaterno;

    @Schema(
            description = "Razón social (solo si es persona jurídica)",
            example = "Empresa Los Andes S.A.C."
    )
    private String razonSocial;

    @Schema(
            description = "Id del tipo de documento (1 = DNI, 2 = RUC, 3 = CE)",
            example = "1"
    )
    private Long tipoDocumento;

    @Schema(
            description = "Número del documento de identidad o RUC",
            example = "12345678"
    )
    private String numeroDocumento;

    @Schema(
            description = "Dirección completa de la persona o empresa",
            example = "Av. Los Olivos 123, Lima"
    )
    private String direccion;

    @Schema(
            description = "Teléfono de contacto",
            example = "999888777"
    )
    private String telefono;

    @Schema(
            description = "Correo electrónico de contacto",
            example = "juan.perez@gmail.com"
    )
    private String correo;
}
