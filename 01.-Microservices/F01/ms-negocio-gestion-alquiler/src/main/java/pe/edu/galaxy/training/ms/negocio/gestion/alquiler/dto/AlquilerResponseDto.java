package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.dto.ClienteResponseDto;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.dto.VehiculoResponseDto;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO de respuesta para el listado de alquiler")
public class AlquilerResponseDto {
    @Schema(description = "Identificador único del alquiler", example = "1001")
    private Long idAlquiler;

    @Schema(description = "Identificador del cliente asociado al alquiler", example = "55")
    private Long idCliente;

    private ClienteResponseDto clienteResponseDto;

    @Schema(description = "Identificador del vehiculo asociado al alquiler", example = "55")
    private Long idVehiculo;

    private VehiculoResponseDto vehiculoResponseDto;

    @Schema(description = "Fecha de inicio del alquiler", example = "23/10/2025")
    private LocalDate fechaInicio;

    @Schema(description = "Fecha de finalización del alquiler", example = "26/10/2025")
    private LocalDate fechaFin;

    @Schema(description = "Subtotal general del alquiler (suma de subtotales de los detalles)", example = "700.00")
    private Double subTotal;

    @Schema(description = "IGV general del alquiler", example = "126.00")
    private Double igv;

    @Schema(description = "Total general del alquiler (subtotal + igv)", example = "826.00")
    private Double total;

    @Schema(description = "Descripción del estado actual del alquiler", example = "Pendiente de pago")
    private String estadoAlquiler;

}
