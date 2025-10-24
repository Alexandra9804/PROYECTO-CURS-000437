package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity.base.BaseEntity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "AlquilerDetalleEntity")
@Table(name = "alquiler_detalle")
@Comment("Tabla que almacena los detalles de los datos de la cabecera para alquilar")
@EqualsAndHashCode(callSuper = true)
public class AlquilerDetalleEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alquiler_detalle")
    private Long idAlquilerDetalle;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_alquiler", nullable = false)
    private AlquilerCabeceraEntity alquilerCabecera;

    @Column(name = "id_vehiculo", nullable = false)
    private Long idVehiculo;

    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "sub_total")
    private Double subTotal;

    @Column(name = "igv")
    private Double igv;

    @Column(name = "total")
    private Double total;
}
