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
@Schema(description = "DTO de solicitud para registrar o actualizar un modelo de vehículo")
public class ModeloRequestDto {

    @Schema(description = "Nombre del modelo", example = "Corolla")
    private String nombre;

    @Schema(description = "Id de la marca", example = "1")
    private Long marcaId;

    @Schema(description = "Descripción del modelo", example = "Sedán compacto de alta eficiencia y confort.")
    private String descripcion;

    @Schema(description = "Año de lanzamiento del modelo", example = "2024")
    private Integer anioLanzamiento;

    @Schema(description = "Versión del modelo", example = "SE Hybrid")
    private String version;

    @Schema(description = "Capacidad de pasajeros", example = "5")
    private Integer capacidadPasajeros;
}
