package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.constant.ExceptionMessages;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.VehiculoRequestDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.VehiculoResponseDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity.ModeloEntity;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.entity.VehiculoEntity;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.exceptions.ResourceNotFoundException;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.mapper.VehiculoMapper;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.repository.ModeloRepository;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.repository.TarifaVehiculoRepository;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.repository.VehiculoRepository;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.services.VehiculoService;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    private final VehiculoMapper vehiculoMapper;

    private final ModeloRepository modeloRepository;

    private final TarifaVehiculoRepository tarifaVehiculoRepository;

    public VehiculoServiceImpl(VehiculoRepository vehiculoRepository, VehiculoMapper vehiculoMapper, ModeloRepository ModeloRepository, TarifaVehiculoRepository tarifaVehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
        this.vehiculoMapper = vehiculoMapper;
        this.modeloRepository = ModeloRepository;
        this.tarifaVehiculoRepository = tarifaVehiculoRepository;
    }

    @Transactional
    @Override
    public VehiculoResponseDto save(VehiculoRequestDto vehiculoRequestDto) {
        VehiculoEntity vehiculoEntity = vehiculoMapper.toEntity(vehiculoRequestDto);

        ModeloEntity modeloEntity = modeloRepository.findById(vehiculoRequestDto.getIdModelo()).orElseThrow(() -> new ResourceNotFoundException(String.format(ExceptionMessages.MODELO_NO_ENCONTRADO,vehiculoRequestDto.getIdModelo())));

        vehiculoEntity.setModeloEntity(modeloEntity);

        return vehiculoMapper.toDTO(vehiculoRepository.save(vehiculoEntity));

    }

    @Transactional(readOnly = true)
    @Override
    public List<VehiculoResponseDto> findAll() {
        return vehiculoRepository.findAll().stream()
                .map(entity -> {
                    VehiculoResponseDto dto = vehiculoMapper.toDTO(entity);

                    tarifaVehiculoRepository
                            .findFirstByVehiculo_IdVehiculoAndFechaFinIsNullAndEstado(entity.getIdVehiculo(), "1")
                            .ifPresent(tarifa -> dto.setTarifaActual(tarifa.getMonto()));

                    return dto;
                })
                .toList();
    }


    @Transactional(readOnly = true)
    @Override
    public VehiculoResponseDto findById(Long id) {
        VehiculoEntity entity = vehiculoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(ExceptionMessages.VEHICULO_NO_ENCONTRADO, id)));

        VehiculoResponseDto dto = vehiculoMapper.toDTO(entity);

        tarifaVehiculoRepository
                .findFirstByVehiculo_IdVehiculoAndFechaFinIsNullAndEstado(entity.getIdVehiculo(), "1")
                .ifPresent(tarifa -> dto.setTarifaActual(tarifa.getMonto()));

        return dto;
    }


}
