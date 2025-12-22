package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.services;

import org.springframework.data.domain.Page;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.MarcaRequestDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.MarcaResponseDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.ModeloComboResponseDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.ModeloResponseDto;

import java.util.List;

public interface MarcaService {
    MarcaResponseDto save(MarcaRequestDto marcaRequestDto);
    List<MarcaResponseDto> findAll();
    MarcaResponseDto findById(Long id);
    Void delete(Long id);
    MarcaResponseDto update(Long idMarca, MarcaRequestDto marcaRequestDto);
    Page<MarcaResponseDto> findByNombre(String nombre, int page, int size);
    List<ModeloComboResponseDto> listModelsByIdMarca(Long idMarca);
}
