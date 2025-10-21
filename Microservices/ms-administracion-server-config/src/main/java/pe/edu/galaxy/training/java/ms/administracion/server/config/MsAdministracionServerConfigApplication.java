package pe.edu.galaxy.training.java.ms.administracion.server.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class MsAdministracionServerConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAdministracionServerConfigApplication.class, args);
	}

}
