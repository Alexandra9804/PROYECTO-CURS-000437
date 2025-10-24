package pe.edu.galaxy.training.ms.negocio.gestion.alquiler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsNegocioGestionAlquilerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsNegocioGestionAlquilerApplication.class, args);
	}

}
