package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Objeto de transferencia de datos que representa una marca.")
public class MarcaResponseDto {
    @Schema(description = "Identificador único de la marca", example = "1")
    private Long idMarca;

    @Schema(description = "Nombre de la marca", example = "Toyota")
    private String nombre;

    @Schema(description = "País de origen de la marca", example = "Japón")
    private String paisOrigen;

    @Schema(description = "Descripción breve de la marca", example = "Fabricante líder mundial en autos confiables.")
    private String descripcion;

    @Schema(description = "Año de fundación de la marca", example = "1937")
    private Integer anioFundacion;
}
