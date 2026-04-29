package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service;

import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto.AlquilerRegistroRequestDto;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto.AlquilerRegistroResponseDto;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto.AlquilerResponseDto;

import java.util.List;

public interface AlquilerService {
    List<AlquilerResponseDto> findAll();
    AlquilerResponseDto findById(Long id);
    AlquilerRegistroResponseDto save(AlquilerRegistroRequestDto requestDto);
}
