package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "vehiculo_alquiler_validacion")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoAlquilerValidacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_validacion")
    private Long idValidacion;

    @Column(name = "id_alquiler", nullable = false, unique = true)
    private Long idAlquiler;

    @Column(name = "id_vehiculo", nullable = false)
    private Long idVehiculo;

    @Column(name = "disponible", nullable = false)
    private Boolean disponible;

    @Column(name = "id_alquiler_estado", nullable = false)
    private Long idAlquilerEstado;

    @Column(name = "mensaje")
    private String mensaje;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}