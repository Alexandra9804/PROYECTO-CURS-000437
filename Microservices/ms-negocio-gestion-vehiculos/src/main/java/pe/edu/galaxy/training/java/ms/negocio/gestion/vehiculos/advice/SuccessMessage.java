package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.advice;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Estructura estándar de respuesta exitosa")
public class SuccessMessage<T> {
    @Schema(description = "Código de estado HTTP", example = "200")
    @NotNull
    private Integer status = 200;

    @Schema(description = "Mensaje de éxito", example = "Operación realizada correctamente")
    @NotNull
    private String message = "OK";

    @Schema(description = "Datos devueltos por la operación")
    private T data;

    @Schema(description = "Información adicional o URI relacionada", example = "uri=/api/v1/vehiculos")
    private String description;
}
