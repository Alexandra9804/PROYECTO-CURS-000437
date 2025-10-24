package pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.entity.base.BaseEntity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "PersonaEntity")
@Table(name = "PERSONA")
@Comment("Tabla que almacena los datos de las personas que son clientes.")
@EqualsAndHashCode(callSuper = true)
public class PersonaEntity extends BaseEntity {

    @Id
    @Column(name = "PERSONA_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPersona")
    @SequenceGenerator(sequenceName = "SEQ_PERSONA", allocationSize = 1, name = "seqPersona")
    private Long idPersona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_PERSONA", nullable = false)
    @Comment("Referencia al tipo de persona: Natural o Jurídica.")
    private TipoPersonaEntity tipoPersona;

    @Column(name = "NOMBRES", length = 100)
    private String nombres;

    @Column(name = "APELLIDO_PATERNO", length = 100)
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO", length = 100)
    private String apellidoMaterno;

    @Column(name = "RAZON_SOCIAL", length = 200)
    private String razonSocial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_DOCUMENTO", nullable = false)
    @Comment("Referencia al tipo de documento.")
    private TipoDocumentoEntity tipoDocumento;

    @Column(name = "NUMERO_DOCUMENTO", unique = true, length = 20)
    private String numeroDocumento;

    @Column(name = "DIRECCION", length = 200)
    private String direccion;

    @Column(name = "TELEFONO", length = 20)
    private String telefono;

    @Column(name = "CORREO", length = 120)
    private String correo;

}
