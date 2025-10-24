package pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Comment;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.entity.base.BaseEntity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "TipoClienteEntity")
@Table(name = "TIPO_CLIENTE")
@Comment("Tabla maestra de tipos de cliente.")
@EqualsAndHashCode(callSuper = true)
public class TipoClienteEntity extends BaseEntity {
    @Id
    @Column(name = "ID_TIPO_CLIENTE")
    private Long idTipoCliente;

    @Column(name = "CODIGO", length = 10, nullable = false, unique = true)
    @Comment("Código del tipo de cliente (FRE, CORP, NUEVO, VIP, etc.)")
    private String codigo;

    @Column(name = "DESCRIPCION", length = 100, nullable = false)
    @Comment("Descripción del tipo de cliente.")
    private String descripcion;
}
