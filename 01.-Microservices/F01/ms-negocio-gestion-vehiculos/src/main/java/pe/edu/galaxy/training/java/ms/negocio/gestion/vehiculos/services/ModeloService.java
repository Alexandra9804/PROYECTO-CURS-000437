package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.services;


import org.springframework.data.domain.Page;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.ModeloRequestDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.ModeloResponseDto;

public interface ModeloService {
    Page<ModeloResponseDto> listarModelos(String nombre, Long idMarca, int page, int size);
    ModeloResponseDto save(ModeloRequestDto modeloRequestDto);

}
