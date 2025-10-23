package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.client.restclient;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.dto.ClienteResponseDto;

@Service
public class ClienteServiceImpl implements ClienteService{

    private final RestClient restClient;

    public ClienteServiceImpl(){
        this.restClient = RestClient.builder()
                //.baseUrl("MS-NEGOCIO-GESTION-CLIENTES/api/v1/clientes")
                .baseUrl("http://localhost:8082/api/v1/clientes")
                .build();
    }

    @Override
    public ClienteResponseDto findById(Long id) {
        return this.restClient
                .get()
                .uri("/{id}", id)
                .retrieve()
                .body(ClienteResponseDto.class);
    }
}
