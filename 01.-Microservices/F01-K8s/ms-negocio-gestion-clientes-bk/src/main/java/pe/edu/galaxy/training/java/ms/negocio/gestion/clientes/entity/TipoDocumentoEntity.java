package pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.entity.base.BaseEntity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "TipoDocumentoEntity")
@Table(name = "TIPO_DOCUMENTO")
@Comment("Tabla maestra de tipos de documento de identidad.")
@EqualsAndHashCode(callSuper = true)
public class TipoDocumentoEntity extends BaseEntity {
    @Id
    @Column(name = "ID_TIPO_DOCUMENTO")
    private Long idTipoDocumento;

    @Column(name = "CODIGO", length = 3, nullable = false, unique = true)
    @Comment("Código del tipo de documento: DNI, RUC, CE, PAS.")
    private String codigo;

    @Column(name = "DESCRIPCION", length = 100, nullable = false)
    @Comment("Descripción del tipo de documento.")
    private String descripcion;
}
