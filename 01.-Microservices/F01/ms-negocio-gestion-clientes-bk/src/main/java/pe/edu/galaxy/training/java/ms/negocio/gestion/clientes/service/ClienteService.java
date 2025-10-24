package pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.service;

import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.dto.ClienteRequestDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.dto.ClienteResponseDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.entity.ClienteEntity;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    ClienteResponseDto save(ClienteRequestDto clienteRequestDto);

    ClienteResponseDto findById(Long id);

    List<ClienteResponseDto> findAll();
}
