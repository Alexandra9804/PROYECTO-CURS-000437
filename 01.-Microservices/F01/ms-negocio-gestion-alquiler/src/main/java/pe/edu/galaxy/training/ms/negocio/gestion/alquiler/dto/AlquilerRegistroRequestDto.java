package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO de solicitud para registrar un alquiler de vehículo")
public class AlquilerRegistroRequestDto {

    @NotNull(message = "El idCliente es obligatorio")
    @Schema(description = "Identificador del cliente que realiza el alquiler", example = "55")
    private Long idCliente;

    @NotNull(message = "El idVehiculo es obligatorio")
    @Schema(description = "Identificador del vehículo a alquilar", example = "10")
    private Long idVehiculo;

    @NotNull(message = "La fechaInicio es obligatoria")
    @Schema(description = "Fecha de inicio del alquiler", example = "2025-10-23")
    private LocalDate fechaInicio;

    @NotNull(message = "La fechaFin es obligatoria")
    @Schema(description = "Fecha de finalización del alquiler", example = "2025-10-26")
    private LocalDate fechaFin;
}