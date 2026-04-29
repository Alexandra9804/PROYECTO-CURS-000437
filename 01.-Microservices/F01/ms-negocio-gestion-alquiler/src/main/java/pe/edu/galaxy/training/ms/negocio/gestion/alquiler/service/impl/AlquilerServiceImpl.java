package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto.AlquilerRegistroRequestDto;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto.AlquilerRegistroResponseDto;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto.AlquilerResponseDto;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity.AlquilerEntity;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity.AlquilerEstadoEntity;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.mapper.AlquilerMapper;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.mapper.AlquilerRegistroMapper;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.producer.AlquilerMessage;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.repository.AlquilerEstadoRepository;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.repository.AlquilerRepository;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.AlquilerService;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.client.restclient.ClienteService;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.client.restclient.VehiculoService;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.dto.ClienteResponseDto;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.dto.VehiculoResponseDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlquilerServiceImpl implements AlquilerService {

    private static final Long ESTADO_PENDIENTE_VALIDACION = 1L;
    private static final double IGV_PORCENTAJE = 0.18;
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final AlquilerRepository cabeceraRepository;
    private final AlquilerEstadoRepository alquilerEstadoRepository;
    private final ClienteService clienteRestClient;
    private final VehiculoService vehiculoRestClient;
    private final AlquilerRegistroMapper alquilerregistroMapper;
    private final AlquilerMapper alquilerMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    public AlquilerServiceImpl(AlquilerRepository cabeceraRepository,
                               AlquilerEstadoRepository alquilerEstadoRepository,
                               ClienteService clienteRestClient,
                               VehiculoService vehiculoRestClient,
                               AlquilerRegistroMapper alquilerRegistroMapper,
                               AlquilerMapper alquilerMapper,
                               ApplicationEventPublisher applicationEventPublisher) {
        this.cabeceraRepository = cabeceraRepository;
        this.alquilerEstadoRepository = alquilerEstadoRepository;
        this.clienteRestClient = clienteRestClient;
        this.vehiculoRestClient = vehiculoRestClient;
        this.alquilerregistroMapper = alquilerRegistroMapper;
        this.alquilerMapper = alquilerMapper;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<AlquilerResponseDto> findAll() {
        return cabeceraRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AlquilerResponseDto findById(Long id) {
        return cabeceraRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Alquiler no encontrado con ID " + id));
    }

    @Override
    @Transactional
    public AlquilerRegistroResponseDto save(AlquilerRegistroRequestDto requestDto) {

        if (requestDto.getFechaFin().isBefore(requestDto.getFechaInicio())) {
            throw new RuntimeException("La fechaFin no puede ser menor que la fechaInicio");
        }

        ClienteResponseDto cliente = clienteRestClient.findById(requestDto.getIdCliente());
        if (cliente == null) {
            throw new RuntimeException("No se encontró el cliente con ID " + requestDto.getIdCliente());
        }

        VehiculoResponseDto vehiculo = vehiculoRestClient.findById(requestDto.getIdVehiculo());
        if (vehiculo == null) {
            throw new RuntimeException("No se encontró el vehículo con ID " + requestDto.getIdVehiculo());
        }

        AlquilerEstadoEntity estadoInicial = alquilerEstadoRepository.findById(ESTADO_PENDIENTE_VALIDACION)
                .orElseThrow(() -> new RuntimeException("No se encontró el estado inicial del alquiler"));

        AlquilerEntity alquilerEntity = alquilerregistroMapper.toEntity(requestDto);

        long diasAlquiler = calcularDias(requestDto.getFechaInicio(), requestDto.getFechaFin());

        double precio = vehiculo.getPrecio();
        double subTotal = redondear(diasAlquiler * precio);
        double igv = redondear(subTotal * IGV_PORCENTAJE);
        double total = redondear(subTotal + igv);

        alquilerEntity.setFechaRegistro(LocalDate.now());
        alquilerEntity.setIdAlquilerEstado(estadoInicial.getIdAlquilerEstado());
        alquilerEntity.setEstadoAlquiler(estadoInicial);
        alquilerEntity.setSubTotal(subTotal);
        alquilerEntity.setIgv(igv);
        alquilerEntity.setTotal(total);

        AlquilerEntity saved = cabeceraRepository.save(alquilerEntity);

        AlquilerEntity entityPersisted = cabeceraRepository.findById(saved.getIdAlquiler())
                .orElseThrow(() -> new RuntimeException("No se pudo recuperar el alquiler recién registrado"));

        AlquilerMessage alquilerMessage = AlquilerMessage.builder()
                .idAlquiler(entityPersisted.getIdAlquiler())
                .idCliente(entityPersisted.getIdCliente())
                .idVehiculo(entityPersisted.getIdVehiculo())
                .fechaInicio(entityPersisted.getFechaInicio().toString())
                .fechaFin(entityPersisted.getFechaFin().toString())
                .estadoAlquiler(
                        entityPersisted.getEstadoAlquiler() != null
                                ? entityPersisted.getEstadoAlquiler().getDescripcion()
                                : estadoInicial.getDescripcion()
                )
                .total(entityPersisted.getTotal())
                .fecha(LocalDateTime.now().format(DATE_TIME_FORMATTER))
                .build();

        applicationEventPublisher.publishEvent(alquilerMessage);

        return alquilerregistroMapper.toDto(entityPersisted);
    }

    private AlquilerResponseDto mapToResponse(AlquilerEntity alquilerEntity) {
        AlquilerResponseDto dto = alquilerMapper.toDto(alquilerEntity);
        dto.setClienteResponseDto(clienteRestClient.findById(alquilerEntity.getIdCliente()));
        dto.setVehiculoResponseDto(vehiculoRestClient.findById(alquilerEntity.getIdVehiculo()));
        return dto;
    }

    private long calcularDias(LocalDate fechaInicio, LocalDate fechaFin) {
        return ChronoUnit.DAYS.between(fechaInicio, fechaFin) + 1;
    }

    private double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}