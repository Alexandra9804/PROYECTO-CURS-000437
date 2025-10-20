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
@Table(name = "tbl_marca")
@Comment("Tabla que almacena las marcas de los vehículos")
@EqualsAndHashCode(callSuper = true)
public class MarcaEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca", nullable = false, updatable = false)
    private Long idMarca;

    @Column(name = "nombre", nullable = false, unique = true, length = 50)
    private String nombre;

    @Column(name = "pais_origen", nullable = true, length = 50)
    private String paisOrigen;

    @OneToMany(mappedBy = "marcaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ModeloEntity> modelos;
}
