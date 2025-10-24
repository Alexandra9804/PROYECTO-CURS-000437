package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLRestriction;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity.base.BaseEntity;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "AlquilerCabeceraEntity")
@Table(name = "alquiler_cabecera")
@Comment("Tabla que almacena los datos de la cabecera para alquilar")
@EqualsAndHashCode(callSuper = true)
public class AlquilerCabeceraEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alquiler")
    private Long idAlquiler;

    // Referencia al cliente (desde ms-clientes)
    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;

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

    // Relación con la tabla maestra de estado
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alquiler_estado", nullable = false)
    private AlquilerEstadoEntity estadoAlquiler;

    // Relación uno a muchos con detalle
    @OneToMany(mappedBy = "alquilerCabecera", cascade = CascadeType.ALL)
    @SQLRestriction("estado = '1'")
    private List<AlquilerDetalleEntity> listAlquilerDetalle;
}
