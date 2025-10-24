package pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.entity.base.BaseEntity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "ClienteEntity")
@Table(name = "CLIENTE")
@Comment("Tabla que almacena los clientes registrados.")
@EqualsAndHashCode(callSuper = true)
public class ClienteEntity extends BaseEntity {
    @Id
    @Column(name = "CLIENTE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCliente")
    @SequenceGenerator(sequenceName = "SEQ_CLIENTE", allocationSize = 1, name = "seqCliente")
    private Long idCliente;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSONA_ID", nullable = false)
    @Comment("Referencia a la persona asociada al cliente.")
    private PersonaEntity persona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_CLIENTE", nullable = false)
    @Comment("Referencia al tipo de cliente.")
    private TipoClienteEntity tipoCliente;

}
