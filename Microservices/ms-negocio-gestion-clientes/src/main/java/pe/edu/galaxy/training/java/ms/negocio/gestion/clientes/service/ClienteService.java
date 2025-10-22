package pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.service;

import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.dto.ClienteRequestDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.dto.ClienteResponseDto;

public interface ClienteService {
    ClienteResponseDto save(ClienteRequestDto clienteRequestDto);
}
