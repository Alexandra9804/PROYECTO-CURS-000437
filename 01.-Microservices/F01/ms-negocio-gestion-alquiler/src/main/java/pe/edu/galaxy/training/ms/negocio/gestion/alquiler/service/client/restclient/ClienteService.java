package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.client.restclient;

import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.dto.ClienteResponseDto;

public interface ClienteService {
    ClienteResponseDto findById(Long id);
}
