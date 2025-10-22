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
public class ClienteRequestDto {

    @Schema(
            description = "Id del tipo de cliente (1 = Regular, 2 = Corporativo, 3 = Premium)",
            example = "1"
    )
    private Long tipoClienteId;

    @Schema(
            description = "Objeto que contiene la información de la persona asociada al cliente",
            example = """
        {
          "tipoPersona": 1,
          "nombres": "Juan Carlos",
          "apellidoPaterno": "Pérez",
          "apellidoMaterno": "Gómez",
          "razonSocial": null,
          "tipoDocumento": 1,
          "numeroDocumento": "12345678",
          "direccion": "Av. Los Olivos 123, Lima",
          "telefono": "999888777",
          "correo": "juan.perez@gmail.com"
        }
        """
    )
    private PersonaRequestDto persona;

}
