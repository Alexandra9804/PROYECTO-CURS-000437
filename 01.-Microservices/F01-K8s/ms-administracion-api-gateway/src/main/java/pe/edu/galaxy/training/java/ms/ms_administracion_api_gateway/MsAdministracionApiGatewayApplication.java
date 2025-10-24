package pe.edu.galaxy.training.java.ms.ms_administracion_api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsAdministracionApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAdministracionApiGatewayApplication.class, args);
	}

}
