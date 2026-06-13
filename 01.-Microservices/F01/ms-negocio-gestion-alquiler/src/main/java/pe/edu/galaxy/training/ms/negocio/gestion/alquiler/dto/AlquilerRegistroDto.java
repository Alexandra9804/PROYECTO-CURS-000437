package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AlquilerRegistroDto {
    private Long idCliente;
    private Long idVehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
