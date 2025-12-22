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
@Schema(description = "DTO de respuesta que contiene la información del modelo de vehículo")
public class ModeloComboResponseDto {
    @Schema(description = "ID único del modelo", example = "10")
    private Long idModelo;

    @Schema(description = "Nombre del modelo", example = "Corolla")
    private String nombre;
}
