package pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.entity.base.BaseEntity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "TipoPersonaEntity")
@Table(name = "TIPO_PERSONA")
@Comment("Tabla maestra que define los tipos de persona.")
@EqualsAndHashCode(callSuper = true)
public class TipoPersonaEntity extends BaseEntity {
    @Id
    @Column(name = "ID_TIPO_PERSONA")
    private Long idTipoPersona;

    @Column(name = "CODIGO", length = 1, nullable = false, unique = true)
    private String codigo;

    @Column(name = "DESCRIPCION", length = 50, nullable = false)
    private String descripcion;
}
