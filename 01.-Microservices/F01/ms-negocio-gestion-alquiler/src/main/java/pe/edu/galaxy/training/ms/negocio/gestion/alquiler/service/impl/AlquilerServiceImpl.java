package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto.AlquilerRegistroDto;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto.AlquilerRegistroRequestDto;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto.AlquilerRegistroResponseDto;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto.AlquilerResponseDto;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity.AlquilerEntity;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity.AlquilerEstadoEntity;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.entity.OutboxEventEntity;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.enums.OutboxStatus;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.events.AlquilerEvent;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.mapper.AlquilerMapper;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.mapper.AlquilerRegistroMapper;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.producer.AlquilerMessage;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.repository.AlquilerEstadoRepository;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.repository.AlquilerRepository;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.repository.OutboxEventRepository;
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
    private final OutboxEventRepository outboxEventRepository;
    private final ObjectMapper objectMapper;


    public AlquilerServiceImpl(AlquilerRepository cabeceraRepository,
                               AlquilerEstadoRepository alquilerEstadoRepository,
                               ClienteService clienteRestClient,
                               VehiculoService vehiculoRestClient,
                               AlquilerRegistroMapper alquilerRegistroMapper,
                               AlquilerMapper alquilerMapper,
                               ApplicationEventPublisher applicationEventPublisher, OutboxEventRepository outboxEventRepository, ObjectMapper objectMapper) {
        this.cabeceraRepository = cabeceraRepository;
        this.alquilerEstadoRepository = alquilerEstadoRepository;
        this.clienteRestClient = clienteRestClient;
        this.vehiculoRestClient = vehiculoRestClient;
        this.alquilerregistroMapper = alquilerRegistroMapper;
        this.alquilerMapper = alquilerMapper;
        this.applicationEventPublisher = applicationEventPublisher;
        this.outboxEventRepository = outboxEventRepository;
        this.objectMapper = objectMapper;
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
    public AlquilerRegistroResponseDto save(AlquilerRegistroDto alquilerRegistroDto) {

        if (alquilerRegistroDto.getFechaFin().isBefore(alquilerRegistroDto.getFechaInicio())) {
            throw new RuntimeException("La fechaFin no puede ser menor que la fechaInicio");
        }

        ClienteResponseDto cliente = clienteRestClient.findById(alquilerRegistroDto.getIdCliente());
        if (cliente == null) {
            throw new RuntimeException("No se encontró el cliente con ID " + alquilerRegistroDto.getIdCliente());
        }

        VehiculoResponseDto vehiculo = vehiculoRestClient.findById(alquilerRegistroDto.getIdVehiculo());
        if (vehiculo == null) {
            throw new RuntimeException("No se encontró el vehículo con ID " + alquilerRegistroDto.getIdVehiculo());
        }

        AlquilerEstadoEntity estadoInicial = alquilerEstadoRepository.findById(ESTADO_PENDIENTE_VALIDACION)
                .orElseThrow(() -> new RuntimeException("No se encontró el estado inicial del alquiler"));

        AlquilerEntity alquilerEntity = alquilerregistroMapper.toEntity(alquilerRegistroDto);

        long diasAlquiler = calcularDias(alquilerRegistroDto.getFechaInicio(), alquilerRegistroDto.getFechaFin());

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

        AlquilerEvent alquilerEvent = alquilerregistroMapper.toEvent(entityPersisted);

        try {
            String payload = objectMapper.writeValueAsString(alquilerEvent);

            OutboxEventEntity outboxEvent = OutboxEventEntity.builder()
                    .aggregateId(entityPersisted.getIdAlquiler())
                    .eventType("ALQUILER_REGISTRADO")
                    .topic("topic-alquiler-registro")
                    .messageKey(String.valueOf(entityPersisted.getIdAlquiler()))
                    .payload(payload)
                    .status(OutboxStatus.PENDIENTE)
                    .failedAttemptCount(0)
                    .createdAt(LocalDateTime.now())
                    .build();

            outboxEventRepository.save(outboxEvent);

        } catch (Exception e) {
            throw new RuntimeException("Error creando evento outbox para alquiler", e);
        }

        applicationEventPublisher.publishEvent(alquilerEvent);

        return alquilerregistroMapper.toResponseDto(entityPersisted);
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