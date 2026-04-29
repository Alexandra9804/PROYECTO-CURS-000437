package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity.base.BaseEntity;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "AlquilerEntity")
@Table(name = "alquiler")
@Comment("Tabla que almacena los datos del alquiler")
@EqualsAndHashCode(callSuper = true)
public class AlquilerEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alquiler")
    private Long idAlquiler;

    // Referencia al cliente (desde ms-clientes)
    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;

    // Referencia al vehiculo (desde ms-vehiculos)
    @Column(name = "id_vehiculo", nullable = false)
    private Long idVehiculo;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "sub_total")
    private Double subTotal;

    @Column(name = "igv")
    private Double igv;

    @Column(name = "total")
    private Double total;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    @Column(name = "id_alquiler_estado", nullable = false)
    private Long idAlquilerEstado;

    // Relación con la tabla maestra de estado
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alquiler_estado", insertable = false, updatable = false)
    private AlquilerEstadoEntity estadoAlquiler;

}
