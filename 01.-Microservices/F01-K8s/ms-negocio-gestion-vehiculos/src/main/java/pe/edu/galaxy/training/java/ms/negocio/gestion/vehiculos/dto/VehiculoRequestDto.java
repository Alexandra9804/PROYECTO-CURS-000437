package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.enums.EstadoVehiculo;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.enums.TipoCombustible;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.enums.TipoTransmision;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO de solicitud para registrar o actualizar un vehículo")
public class VehiculoRequestDto {
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

    @Schema(description = "Tipo de combustible (GASOLINA, DIESEL, ELECTRICO, HIBRIDO)")
    private TipoCombustible tipoCombustible;

    @Schema(description = "Tipo de transmisión (MANUAL o AUTOMATICA)")
    private TipoTransmision transmision;

    @Schema(description = "Estado actual del vehículo", example = "DISPONIBLE")
    private EstadoVehiculo estadoVehiculo;

    @Schema(description = "Identificador del modelo asociado", example = "3")
    private Long idModelo;
}
