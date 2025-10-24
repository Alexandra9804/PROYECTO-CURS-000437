package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO de solicitud para registrar el detalle del alquiler de un vehiculo")
public class AlquilerDetalleRequestDto {

    @Schema(description = "Identificador del vehículo a alquilar", example = "1")
    private Long idVehiculo;

    @Schema(description = "Cantidad de unidades a alquilar", example = "2")
    private Integer cantidad;

}
