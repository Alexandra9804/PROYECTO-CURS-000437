package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.enums.EstadoVehiculo;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.enums.TipoCombustible;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.enums.TipoTransmision;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Objeto de transferencia de datos que representa un vehículo.")
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

    @Schema(description = "Tipo de combustible (GASOLINA, DIESEL, ELECTRICO, HIBRIDO)")
    private TipoCombustible tipoCombustible;

    @Schema(description = "Tipo de transmisión (MANUAL o AUTOMATICA)")
    private TipoTransmision transmision;

    @Schema(description = "Estado actual del vehículo", example = "DISPONIBLE")
    private EstadoVehiculo estadoVehiculo;

    @Schema(description = "Id del modelo asociado", example = "3")
    private Long idModelo;

    @Schema(description = "Nombre del modelo del vehículo", example = "Corolla")
    private String modeloNombre;

    @Schema(description = "Id de la marca", example = "3")
    private Long idMarca;

    @Schema(description = "Nombre de la marca del vehículo", example = "Toyota")
    private String marcaNombre;

   /*@Schema(description = "Tarifa diaria vigente del vehículo", example = "250.00")
    private BigDecimal tarifaActual;*/

    @Schema(description = "Precio del vehículo", example = "50000")
    private BigDecimal precio;
}
