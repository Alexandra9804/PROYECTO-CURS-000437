package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.constant.ExceptionMessages;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.MarcaRequestDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.MarcaResponseDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.VehiculoResponseDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity.MarcaEntity;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity.ModeloEntity;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity.VehiculoEntity;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.exceptions.ResourceNotFoundException;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.mapper.MarcaMapper;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.repository.MarcaRepository;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.services.MarcaService;

import java.util.List;

@Service
public class MarcaServiceImpl implements MarcaService {

    private final MarcaRepository marcaRepository;
    private final MarcaMapper marcaMapper;

    public MarcaServiceImpl(MarcaRepository marcaRepository, MarcaMapper marcaMapper) {
        this.marcaRepository = marcaRepository;
        this.marcaMapper = marcaMapper;
    }

    @Override
    public MarcaResponseDto save(MarcaRequestDto marcaRequestDto) {
        MarcaEntity marcaEntity = marcaMapper.toEntity(marcaRequestDto);

        return marcaMapper.toDTO(marcaRepository.save(marcaEntity));
    }

    @Override
    public List<MarcaResponseDto> findAll() {
        return marcaRepository.findAll().stream()
                .map(marcaMapper::toDTO)
                .toList();
    }

    @Override
    public MarcaResponseDto findById(Long id) {
        MarcaEntity entity = marcaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(ExceptionMessages.VEHICULO_NO_ENCONTRADO, id)));

        MarcaResponseDto dto = marcaMapper.toDTO(entity);

        return dto;
    }

    @Override
    public Void delete(Long id) {
        marcaRepository.deleteById(id);
        return null;
    }

    @Override
    public MarcaResponseDto update(Long idMarca, MarcaRequestDto marcaRequestDto) {
        MarcaEntity entity = marcaRepository.findById(idMarca)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(ExceptionMessages.VEHICULO_NO_ENCONTRADO, idMarca)));

        entity.setNombre(marcaRequestDto.getNombre());
        entity.setPaisOrigen(marcaRequestDto.getPaisOrigen());
        entity.setDescripcion(marcaRequestDto.getDescripcion());
        entity.setAnioFundacion(marcaRequestDto.getAnioFundacion());

    return marcaMapper.toDTO(marcaRepository.save(entity));
    }

    @Override
    public Page<MarcaResponseDto> findByNombre(String nombre, int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("idMarca").ascending());

        return marcaRepository
                .findByNombreContainingIgnoreCase(nombre, pageable)
                .map(marcaMapper::toDTO);
    }
}
