package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.services;

import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.VehiculoRequestDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.VehiculoResponseDto;

import java.util.List;
import java.util.Optional;

public interface VehiculoService {
    VehiculoResponseDto save(VehiculoRequestDto vehiculoRequestDto);

    List<VehiculoResponseDto> findAll();

    Optional<VehiculoResponseDto> findById(Long id);
}
