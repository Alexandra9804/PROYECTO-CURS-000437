package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity.base.BaseEntity;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.enums.EstadoVehiculo;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.enums.TipoCombustible;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.enums.TipoTransmision;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "VehiculoEntity")
@Table(name = "tbl_vehiculo")
@Comment("Tabla que almacena los datos de los vehículos registrados para alquiler")
@EqualsAndHashCode(callSuper = true)
public class VehiculoEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo", nullable = false, updatable = false)
    private Long idVehiculo;

    @Column(name = "placa", nullable = false, unique = true, length = 6)
    private String placa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_modelo", nullable = false)
    private ModeloEntity modeloEntity;

    @Column(name = "anio", nullable = false)
    private Integer anio;

    @Column(name = "color", nullable = true, length = 30)
    private String color;

    @Column(name = "kilometraje", nullable = false)
    private Long kilometraje;

    @Column(name = "vin", nullable = true, unique = true, length = 17)
    private String vin;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_combustible", nullable = false, length = 15)
    private TipoCombustible tipoCombustible;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_transmision", nullable = false, length = 15)
    private TipoTransmision transmision;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_vehiculo", nullable = false, length = 20)
    private EstadoVehiculo estadoVehiculo;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    private List<TarifaVehiculoEntity> tarifas;
}
