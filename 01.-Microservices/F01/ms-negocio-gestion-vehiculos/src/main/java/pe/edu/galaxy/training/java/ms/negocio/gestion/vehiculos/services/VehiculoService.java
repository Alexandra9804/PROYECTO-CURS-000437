package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.services;

import org.springframework.data.domain.Page;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.*;

import java.util.List;
import java.util.Optional;

public interface VehiculoService {
    VehiculoResponseDto save(VehiculoRequestDto vehiculoRequestDto);

    VehiculoResponseDto update(Long idVehiculo, VehiculoRequestDto vehiculoRequestDto );

    List<VehiculoResponseDto> findAll();

    VehiculoResponseDto findById(Long id);

    Page<VehiculoResponseDto> listarVehiculos(String vin, Long idMarca, Long idModelo, int page, int size);

    Void delete(Long id);

}
