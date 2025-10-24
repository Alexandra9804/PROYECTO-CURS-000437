package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity.base.BaseEntity;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_modelo")
@Comment("Tabla que almacena los modelos de vehículos asociados a una marca")
@EqualsAndHashCode(callSuper = true)
public class ModeloEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modelo", nullable = false, updatable = false)
    private Long idModelo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_marca", nullable = false)
    private MarcaEntity marcaEntity;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "descripcion", length = 100)
    private String descripcion;

    @OneToMany(mappedBy = "modeloEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VehiculoEntity> vehiculos;
}
