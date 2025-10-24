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
@Schema(description = "DTO de solicitud para registrar un alquiler de vehiculo")
public class AlquilerCabeceraRequestDto {
    @Schema(description = "Identificador del cliente que realiza el alquiler", example = "55")
    private Long idCliente;

    @Schema(description = "Fecha de inicio del alquiler", example = "23/10/2025")
    private LocalDate fechaInicio;

    @Schema(description = "Fecha de finalización del alquiler", example = "26/10/2025")
    private LocalDate fechaFin;

    @Schema(description = "Identificador del estado inicial del alquiler (ej. Pendiente de pago)", example = "1")
    private Long idEstadoAlquiler;

    @Schema(description = "Lista de vehículos a alquilar", example = "[ { \"idVehiculo\": 101, \"cantidad\": 2 } ]")
    private List<AlquilerDetalleRequestDto> detalles;
}
