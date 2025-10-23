package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service;

import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto.AlquilerCabeceraResponseDto;

import java.util.List;

public interface AlquilerService {
    List<AlquilerCabeceraResponseDto> findAll();
    AlquilerCabeceraResponseDto findById(Long id);
}
