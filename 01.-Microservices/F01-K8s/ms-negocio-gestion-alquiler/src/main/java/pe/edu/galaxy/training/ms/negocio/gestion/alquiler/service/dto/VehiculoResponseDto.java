package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO de respuesta para el vehiculo")
public class VehiculoResponseDto {
    @Schema(description = "Identificador único del vehículo", example = "1")
    private Long idVehiculo;

    @Schema(description = "Placa del vehículo", example = "ABC123")
    private String placa;

    @Schema(description = "Año de fabricación", example = "2022")
    private Integer anio;

    @Schema(description = "Color del vehículo", example = "Rojo metálico")
    private String color;

    @Schema(description = "Kilometraje actual", example = "45000")
    private Long kilometraje;

    @Schema(description = "Número VIN del vehículo (17 caracteres)", example = "5YJ3E1EA7JF000000")
    private String vin;

    @Schema(description = "Tarifa diaria vigente del vehículo", example = "250.00")
    private Integer tarifaActual;
}
