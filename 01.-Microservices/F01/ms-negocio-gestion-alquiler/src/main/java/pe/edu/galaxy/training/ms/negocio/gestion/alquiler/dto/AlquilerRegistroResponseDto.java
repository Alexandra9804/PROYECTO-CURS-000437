package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO de respuesta resumida del alquiler registrado")
public class AlquilerRegistroResponseDto {

    @Schema(description = "Identificador del alquiler", example = "1001")
    private Long idAlquiler;

    @Schema(description = "Identificador del cliente", example = "55")
    private Long idCliente;

    @Schema(description = "Identificador del vehículo", example = "10")
    private Long idVehiculo;

    @Schema(description = "Id del estado actual del alquiler", example = "PENDIENTE_VALIDACION")
    private Long idAlquilerEstado;

    @Schema(description = "Estado actual del alquiler", example = "PENDIENTE_VALIDACION")
    private String estadoAlquiler;

    @Schema(description = "Fecha de inicio del alquiler", example = "2025-10-23")
    private LocalDate fechaInicio;

    @Schema(description = "Fecha de fin del alquiler", example = "2025-10-26")
    private LocalDate fechaFin;

    @Schema(description = "Total del alquiler", example = "354.00")
    private Double total;

    @Schema(description = "Fecha de registro", example = "2025-10-20")
    private LocalDate fechaRegistro;
}