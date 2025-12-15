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
public class ModeloResponseDto {

    @Schema(description = "ID único del modelo", example = "10")
    private Long idModelo;

    @Schema(description = "Nombre del modelo", example = "Corolla")
    private String nombre;

    @Schema(description = "Descripción del modelo", example = "Sedán compacto eficiente y confiable")
    private String descripcion;

    @Schema(description = "Año de lanzamiento del modelo", example = "2024")
    private Integer anioLanzamiento;

    @Schema(description = "Versión del modelo", example = "SE Hybrid")
    private String version;

    @Schema(description = "Capacidad de pasajeros", example = "5")
    private Integer capacidadPasajeros;

    @Schema(description = "ID de la marca", example = "1")
    private Long marcaId;

    @Schema(description = "Nombre de la marca", example = "Toyota")
    private String marcaNombre;

}
