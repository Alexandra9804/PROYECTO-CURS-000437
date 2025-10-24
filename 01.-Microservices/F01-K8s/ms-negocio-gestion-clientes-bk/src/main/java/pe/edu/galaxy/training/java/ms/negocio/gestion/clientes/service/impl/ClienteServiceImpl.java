package pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.dto.ClienteRequestDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.dto.ClienteResponseDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.entity.*;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.mapper.ClienteMapper;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.repository.*;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.service.ClienteService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final PersonaRepository personaRepository;
    private final ClienteMapper clienteMapper;
    private final TipoClienteRepository tipoClienteRepository;
    private final TipoPersonaRepository tipoPersonaRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;

    public ClienteServiceImpl(
            ClienteRepository clienteRepository,
            PersonaRepository personaRepository,
            ClienteMapper clienteMapper,
            TipoClienteRepository tipoClienteRepository,
            TipoPersonaRepository tipoPersonaRepository,
            TipoDocumentoRepository tipoDocumentoRepository){
        this.clienteRepository = clienteRepository;
        this.personaRepository = personaRepository;
        this.clienteMapper = clienteMapper;
        this.tipoClienteRepository = tipoClienteRepository;
        this.tipoPersonaRepository = tipoPersonaRepository;
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    }

    @Transactional
    @Override
    public ClienteResponseDto save(ClienteRequestDto dto) {

        ClienteEntity cliente = clienteMapper.toEntity(dto);

        TipoClienteEntity tipoCliente = tipoClienteRepository.findById(dto.getTipoClienteId())
                .orElseThrow(() -> new RuntimeException("Tipo de cliente no encontrado"));

        TipoPersonaEntity tipoPersona = tipoPersonaRepository.findById(dto.getPersona().getTipoPersona())
                .orElseThrow(() -> new RuntimeException("Tipo de persona no encontrado"));

        TipoDocumentoEntity tipoDocumento = tipoDocumentoRepository.findById(dto.getPersona().getTipoDocumento())
                .orElseThrow(() -> new RuntimeException("Tipo de documento no encontrado"));

        PersonaEntity persona = cliente.getPersona();
        persona.setTipoPersona(tipoPersona);
        persona.setTipoDocumento(tipoDocumento);

        cliente.setTipoCliente(tipoCliente);

        personaRepository.save(persona);
        ClienteEntity saved = clienteRepository.save(cliente);

        return clienteMapper.toDTO(saved);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ClienteResponseDto> findAll() {
        return clienteRepository.findAll().stream()
                .map(clienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public ClienteResponseDto findById(Long id) {
        ClienteEntity entity = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
        return clienteMapper.toDTO(entity);
    }
}
