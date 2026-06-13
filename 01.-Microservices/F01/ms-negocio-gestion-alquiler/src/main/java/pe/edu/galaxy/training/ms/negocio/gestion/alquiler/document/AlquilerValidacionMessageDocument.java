package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "alquiler-validacion-message")
public class AlquilerValidacionMessageDocument {

    @MongoId(FieldType.OBJECT_ID)
    private String _id;

    @Field("idAlquiler")
    private Long idAlquiler;

    @Field("idVehiculo")
    private Long idVehiculo;

    @Field("idAlquilerEstado")
    private Long idAlquilerEstado;

    @Field("mensaje")
    private String mensaje;

    @Field("fecha")
    private String fecha;

    @Field("situacion")
    private String situacion;

    @Field("estado")
    private String estado;
}