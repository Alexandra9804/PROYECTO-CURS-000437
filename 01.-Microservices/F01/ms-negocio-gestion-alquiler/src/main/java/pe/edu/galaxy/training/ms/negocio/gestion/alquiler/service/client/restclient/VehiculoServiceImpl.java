package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.client.restclient;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.dto.VehiculoResponseDto;

@Service
public class VehiculoServiceImpl implements VehiculoService{

    private final RestClient restClient;

    public VehiculoServiceImpl(){
        this.restClient = RestClient.builder()
                //.baseUrl("ms-vehiculos/api/v1/vehiculos")
                .baseUrl("http://localhost:8081/api/v1/vehiculos")
                .build();
    }
    @Override
    public VehiculoResponseDto findById(Long id) {
        return this.restClient
                .get()
                .uri("/{id}", id)
                .retrieve()
                .body(VehiculoResponseDto.class);
    }
}
