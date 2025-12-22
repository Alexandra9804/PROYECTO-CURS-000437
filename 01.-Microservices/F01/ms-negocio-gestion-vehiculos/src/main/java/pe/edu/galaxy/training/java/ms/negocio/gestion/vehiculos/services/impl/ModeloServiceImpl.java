package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.constant.ExceptionMessages;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.MarcaResponseDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.ModeloRequestDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.ModeloResponseDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity.MarcaEntity;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity.ModeloEntity;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.exceptions.ResourceNotFoundException;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.mapper.ModeloMapper;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.repository.MarcaRepository;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.repository.ModeloRepository;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.services.ModeloService;

import java.util.List;

@Service
public class ModeloServiceImpl implements ModeloService {

    private final ModeloRepository modeloRepository;
    private final MarcaRepository marcaRepository;
    private final ModeloMapper modeloMapper;

    public ModeloServiceImpl(ModeloRepository modeloRepository, MarcaRepository marcaRepository, ModeloMapper modeloMapper) {
        this.modeloRepository = modeloRepository;
        this.marcaRepository = marcaRepository;
        this.modeloMapper = modeloMapper;
    }

    @Override
    public Page<ModeloResponseDto> listarModelos(String nombre, Long idMarca, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("idModelo").ascending());

        return modeloRepository.buscarModelos(nombre, idMarca, pageable)
                .map(modeloMapper::toDTO);
    }

    @Override
    public ModeloResponseDto save(ModeloRequestDto modeloRequestDto) {
        MarcaEntity marcaEntity = marcaRepository.findById(modeloRequestDto.getMarcaId()).orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.MARCA_NO_ENCONTRADO));
        ModeloEntity modeloEntity = modeloMapper.toEntity(modeloRequestDto);
        modeloEntity.setMarcaEntity(marcaEntity);

        return modeloMapper.toDTO(modeloRepository.save(modeloEntity));
    }

    @Override
    public ModeloResponseDto update(Long idModelo, ModeloRequestDto modeloRequestDto) {
        ModeloEntity modeloEntity = modeloRepository.findById(idModelo)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(ExceptionMessages.MODELO_NO_ENCONTRADO, idModelo)));

        MarcaEntity marcaEntity = marcaRepository.findById(modeloRequestDto.getMarcaId())
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.MARCA_NO_ENCONTRADO));

        modeloEntity.setNombre(modeloRequestDto.getNombre());
        modeloEntity.setMarcaEntity(marcaEntity);
        modeloEntity.setDescripcion(modeloRequestDto.getDescripcion());
        modeloEntity.setAnioLanzamiento(modeloRequestDto.getAnioLanzamiento());
        modeloEntity.setVersion(modeloRequestDto.getVersion());
        modeloEntity.setCapacidadPasajeros(modeloRequestDto.getCapacidadPasajeros());

        return modeloMapper.toDTO(modeloRepository.save(modeloEntity));
    }

    @Override
    public ModeloResponseDto findById(Long id) {
        ModeloEntity entity = modeloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(ExceptionMessages.MODELO_NO_ENCONTRADO, id)));

        ModeloResponseDto dto = modeloMapper.toDTO(entity);

        return dto;
    }

    @Override
    public Void delete(Long id) {
        modeloRepository.deleteById(id);
        return null;
    }

}
