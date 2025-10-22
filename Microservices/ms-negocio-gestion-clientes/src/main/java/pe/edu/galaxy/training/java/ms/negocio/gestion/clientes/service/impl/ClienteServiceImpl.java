package pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.service.impl;

import org.springframework.stereotype.Service;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.dto.ClienteRequestDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.dto.ClienteResponseDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.entity.ClienteEntity;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.entity.PersonaEntity;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.mapper.ClienteMapper;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.repository.ClienteRepository;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.repository.PersonaRepository;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final PersonaRepository personaRepository;
    private final ClienteMapper clienteMapper;

    public ClienteServiceImpl(ClienteRepository clienteRepository, PersonaRepository personaRepository, ClienteMapper clienteMapper){
        this.clienteRepository = clienteRepository;
        this.personaRepository = personaRepository;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public ClienteResponseDto save(ClienteRequestDto clienteRequestDto) {
        ClienteEntity clienteEntity = clienteMapper.toEntity(clienteRequestDto);

        PersonaEntity personaGuardada = personaRepository.save(clienteEntity.getPersona());

        clienteEntity.setPersona(personaGuardada);
        ClienteEntity clienteGuardado = clienteRepository.save(clienteEntity);

        return clienteMapper.toDTO(clienteGuardado);
    }
}
