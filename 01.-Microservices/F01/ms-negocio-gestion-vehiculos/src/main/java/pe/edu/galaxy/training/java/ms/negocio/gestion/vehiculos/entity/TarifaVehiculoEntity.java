package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity.base.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_tarifa_vehiculo")
@Comment("Tabla que almacena los modelos de vehículos asociados a una marca")
@EqualsAndHashCode(callSuper = true)
public class TarifaVehiculoEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarifa_vehiculo", nullable = false, updatable = false)
    private Long idTarifaVehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehiculo", nullable = false)
    private VehiculoEntity vehiculo;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = true)
    private LocalDate fechaFin; // NULL = es el vigente actualmente

    @Column(name = "motivo_cambio", nullable = true, length = 100)
    private String motivoCambio;
}
