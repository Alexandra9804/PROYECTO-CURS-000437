package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.advice;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorMessage {
    @Schema(description = "Status del error", example = "403")
    @NotNull
    private Integer status;

    @Schema(description = "Mensaje describiendo el error", example = "Sucedió un error")
    @NotNull
    private String message;

    @Schema(description = "Datos adicionales", nullable = true, defaultValue = "null", example = "null")
    private String data = null;

    @Schema(description = "Descripción adicional", nullable = true, defaultValue = "null", example = "uri=/api/v1/files")
    @NotNull
    private String description = null;
}
