package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.client.restclient;

import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.dto.ClienteResponseDto;

@Service
public class ClienteServiceImpl implements ClienteService{

    private final RestClient restClient;

    private final CircuitBreaker circuitBreaker;

    private final String ms_name = "ms-negocio-gestion-clientes";

    public ClienteServiceImpl(CircuitBreakerFactory<?, ?> circuitBreakerFactory){
        this.restClient = RestClient.builder()
                //.baseUrl("ms-clientes/api/v1/clientes")
                //.baseUrl("http://localhost:8082/api/v1/clientes")
                .build();
        this.circuitBreaker = circuitBreakerFactory.create(ms_name);
    }

    @Override
    public ClienteResponseDto findById(Long id) {
        return circuitBreaker.run(
                () -> {
                    return this.restClient
                            .get()
                            .uri("http://localhost:8082/api/v1/clientes/{id}", id)
                            .retrieve()
                            .body(ClienteResponseDto.class);
                },
                    throwable -> findByIdBk(id)
        );


    }

    private ClienteResponseDto findByIdBk(Long id){
        return circuitBreaker.run(
                () -> {
                    return this.restClient
                            .get()
                            .uri("http://localhost:8084/api/v1/clientes/{id}", id)
                            .retrieve()
                            .body(ClienteResponseDto.class);
                },
                throwable -> getByMemory(id)
        );
    }

    public ClienteResponseDto getByMemory(Long id){
        return new ClienteResponseDto(
                id,                   // idCliente
                "Cliente en memoria",         // nombres
                "Desconocido",                // apellidoPaterno
                "Desconocido",                // apellidoMaterno
                "N/A",                        // razonSocial
                "Natural",                    // tipoPersonaDescripcion
                "N/A",                        // tipoDocumentoDescripcion
                "00000000",                   // numeroDocumento
                "Sin dirección",              // direccion
                "000000000",                  // telefono
                "sin-correo@enmemoria.local",  // correo
                1                             // estado (activo)
        );
    }
}
