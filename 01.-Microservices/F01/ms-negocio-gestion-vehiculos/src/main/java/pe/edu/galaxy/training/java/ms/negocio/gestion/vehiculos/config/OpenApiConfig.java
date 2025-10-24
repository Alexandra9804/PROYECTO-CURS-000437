package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "API de Gestión de Vehículos",
                version = "1.0.0",
                description = "Microservicio encargado de la gestión de vehículos.",
                contact = @Contact(
                        name = "Estudiante",
                        email = "prueba@gmail.com"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8081", description = "Servidor local de desarrollo"),
        }
)
public class OpenApiConfig {
}
