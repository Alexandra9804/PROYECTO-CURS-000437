package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.client.restclient;

import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.dto.VehiculoResponseDto;

public interface VehiculoService {
    VehiculoResponseDto findById(Long id);
}
