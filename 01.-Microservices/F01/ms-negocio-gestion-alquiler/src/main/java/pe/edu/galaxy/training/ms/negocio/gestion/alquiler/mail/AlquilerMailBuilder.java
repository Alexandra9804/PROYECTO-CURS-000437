package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.mail;

import org.springframework.stereotype.Component;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.consumers.message.AlquilerValidacionMessage;

@Component
public class AlquilerMailBuilder {

    public String buildResultadoAlquilerMessage(AlquilerValidacionMessage message) {
        return """
                <h1>Resultado de alquiler</h1>
                <p>El alquiler con código <strong>%s</strong> fue procesado.</p>
                <p>Estado asignado: <strong>%s</strong></p>
                <p>Mensaje: %s</p>
                <br/>
                <p>Gracias por usar nuestro sistema.</p>
                """.formatted(
                message.getIdAlquiler(),
                message.getIdAlquilerEstado(),
                message.getMensaje()
        );
    }
}
