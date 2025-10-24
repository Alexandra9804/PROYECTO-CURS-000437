package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.dto.VehiculoResponseDto;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO de respuesta para la solicitud al registrar el detalle del alquiler de un vehiculo")
public class AlquilerDetalleResponseDto {

    @Schema(description = "Identificador único del detalle del alquiler", example = "1")
    private Long idAlquilerDetalle;

    @Schema(description = "Identificador del vehículo alquilado", example = "1")
    private Long idVehiculo;

    private VehiculoResponseDto vehiculoResponseDto;

    @Schema(description = "Precio unitario del vehículo en el momento del alquiler", example = "350.00")
    private Double precioUnitario;

    @Schema(description = "Cantidad de unidades alquiladas", example = "2")
    private Integer cantidad;

    @Schema(description = "Subtotal del detalle (precioUnitario * cantidad)", example = "700.00")
    private Double subTotal;

    @Schema(description = "IGV calculado para el detalle", example = "126.00")
    private Double igv;

    @Schema(description = "Total del detalle (subtotal + igv)", example = "826.00")
    private Double total;
}
