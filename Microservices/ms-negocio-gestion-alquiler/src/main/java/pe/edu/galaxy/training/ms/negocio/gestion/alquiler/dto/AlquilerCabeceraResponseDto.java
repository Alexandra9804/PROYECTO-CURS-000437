package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.dto.ClienteResponseDto;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO de respuesta para el alquiler de un vehiculo")
public class AlquilerCabeceraResponseDto {
    @Schema(description = "Identificador único del alquiler", example = "1001")
    private Long idAlquiler;

    @Schema(description = "Identificador del cliente asociado al alquiler", example = "55")
    private Long idCliente;

    private ClienteResponseDto clienteResponseDto;

    @Schema(description = "Fecha de inicio del alquiler", example = "2025-10-23")
    private LocalDate fechaInicio;

    @Schema(description = "Fecha de finalización del alquiler", example = "2025-10-26")
    private LocalDate fechaFin;

    @Schema(description = "Subtotal general del alquiler (suma de subtotales de los detalles)", example = "700.00")
    private Double subTotal;

    @Schema(description = "IGV general del alquiler", example = "126.00")
    private Double igv;

    @Schema(description = "Total general del alquiler (subtotal + igv)", example = "826.00")
    private Double total;

    @Schema(description = "Descripción del estado actual del alquiler", example = "Pendiente de pago")
    private String estadoAlquiler;

    @Schema(description = "Lista de detalles asociados al alquiler")
    private List<AlquilerDetalleResponseDto> detalles;
}
