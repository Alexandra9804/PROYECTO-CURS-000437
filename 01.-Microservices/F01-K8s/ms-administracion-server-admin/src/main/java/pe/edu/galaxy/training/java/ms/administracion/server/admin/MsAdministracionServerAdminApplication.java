package pe.edu.galaxy.training.java.ms.administracion.server.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class MsAdministracionServerAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAdministracionServerAdminApplication.class, args);
	}

}
