package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity.base.BaseEntity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "AlquilerEstadoEntity")
@Table(name = "alquiler_estado")
@Comment("Tabla que almacena los estados del alquiler")
@EqualsAndHashCode(callSuper = true)
public class AlquilerEstadoEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alquiler_estado")
    private Long idAlquilerEstado;

    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;

}
