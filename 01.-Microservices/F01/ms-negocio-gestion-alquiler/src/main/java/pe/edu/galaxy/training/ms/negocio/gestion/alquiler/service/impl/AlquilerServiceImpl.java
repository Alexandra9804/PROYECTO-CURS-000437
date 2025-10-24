package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.impl;

import org.springframework.stereotype.Service;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto.AlquilerCabeceraResponseDto;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto.AlquilerDetalleResponseDto;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity.AlquilerCabeceraEntity;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.mapper.AlquilerDetalleMapper;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.repository.AlquilerCabeceraRepository;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.AlquilerService;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.client.restclient.ClienteService;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.client.restclient.VehiculoService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlquilerServiceImpl implements AlquilerService {

    private final AlquilerCabeceraRepository cabeceraRepository;
    private final AlquilerDetalleMapper detalleMapper;
    private final ClienteService clienteRestClient;
    private final VehiculoService vehiculoRestClient;

    public AlquilerServiceImpl(AlquilerCabeceraRepository cabeceraRepository,
                               AlquilerDetalleMapper detalleMapper,
                               ClienteService clienteRestClient,
                               VehiculoService vehiculoRestClient
                               ){
        this.cabeceraRepository = cabeceraRepository;
        this.detalleMapper = detalleMapper;
        this.clienteRestClient = clienteRestClient;
        this.vehiculoRestClient = vehiculoRestClient;
    }
    @Override
    public List<AlquilerCabeceraResponseDto> findAll() {
        return cabeceraRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AlquilerCabeceraResponseDto findById(Long id) {
        return cabeceraRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Alquiler no encontrado con ID " + id));
    }

    private AlquilerCabeceraResponseDto mapToResponse(AlquilerCabeceraEntity cabecera) {
        AlquilerCabeceraResponseDto dto = new AlquilerCabeceraResponseDto();
        dto.setIdAlquiler(cabecera.getIdAlquiler());
        dto.setIdCliente(cabecera.getIdCliente());
        dto.setClienteResponseDto(clienteRestClient.findById(cabecera.getIdCliente()));
        dto.setFechaInicio(cabecera.getFechaInicio());
        dto.setFechaFin(cabecera.getFechaFin());
        dto.setSubTotal(cabecera.getSubTotal());
        dto.setIgv(cabecera.getIgv());
        dto.setTotal(cabecera.getTotal());
        dto.setEstadoAlquiler(cabecera.getEstadoAlquiler().getDescripcion());

        dto.setDetalles(
                cabecera.getListAlquilerDetalle().stream().map(detalle -> {
                    AlquilerDetalleResponseDto detDto = detalleMapper.toDto(detalle);
                    detDto.setVehiculoResponseDto(vehiculoRestClient.findById(detalle.getIdVehiculo()));
                    return detDto;
                }).collect(Collectors.toList())
        );

        return dto;
    }
}
