package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.advice.SuccessMessage;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.VehiculoRequestDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.VehiculoResponseDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.services.VehiculoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehiculos")
@Tag(name = "Gestión de Vehículos", description = "Operaciones sobre los vehículos")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @Operation(
            method = "POST",
            summary = "Registrar un nuevo vehículo",
            description = "API encargada de registrar un nuevo vehículo en el sistema. "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Vehículo registrado correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = VehiculoController.SuccessVehiculoResponse.class))
    )
    @PostMapping
    public ResponseEntity<VehiculoResponseDto> registrar(@Valid @RequestBody VehiculoRequestDto dto) {
        VehiculoResponseDto response = vehiculoService.save(dto);
        return ResponseEntity.ok(response);
    }

    @Operation(
            method = "GET",
            summary = "Lista de vehículos registrados",
            description = "API encargada de listar todos los vehículos registrados en el sistema. "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista obtenida correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = VehiculoController.SuccessListVehiculoResponse.class))
    )
    @GetMapping
    public ResponseEntity<List<VehiculoResponseDto>> listar() {
        List<VehiculoResponseDto> lista = vehiculoService.findAll();
        return ResponseEntity.ok(lista);
    }

    @Operation(
            method = "GET",
            summary = "Obtener un vehículo por su ID",
            description = "API encargada de obtener la información detallada de un vehículo a partir de su id."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Vehículo encontrado correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = VehiculoController.SuccessVehiculoResponse.class))
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<VehiculoResponseDto> obtenerPorId(
            @Parameter(description = "Identificador único del vehículo", required = true, example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(vehiculoService.findById(id));

    }

    @Operation(
            method = "GET",
            summary = "Listar vehículos filtrando opcionalmente por VIN, marca y/o modelo",
            description = "API encargada de listar los vehículos aplicando filtros opcionales por VIN (búsqueda parcial), identificador de marca y/o identificador de modelo. Los resultados están paginados."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Listado obtenido correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = VehiculoController.SuccessPageVehiculoResponse.class)
            )
    )
    @GetMapping("/search")
    public ResponseEntity<Page<VehiculoResponseDto>> buscarVehiculos(
            @Parameter(
                    description = "Texto para filtrar por VIN del vehículo (opcional)",
                    example = "5YJ3E1"
            )
            @RequestParam(required = false) String vin,

            @Parameter(
                    description = "ID de la marca para filtrar vehículos (opcional)",
                    example = "1"
            )
            @RequestParam(required = false) Long idMarca,

            @Parameter(
                    description = "ID del modelo para filtrar vehículos (opcional)",
                    example = "3"
            )
            @RequestParam(required = false) Long idModelo,

            @Parameter(
                    description = "Número de página (inicia en 0)",
                    example = "0"
            )
            @RequestParam(defaultValue = "0") int page,

            @Parameter(
                    description = "Cantidad de registros por página",
                    example = "10"
            )
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<VehiculoResponseDto> resultado =
                vehiculoService.listarVehiculos(vin, idMarca, idModelo, page, size);

        return ResponseEntity.ok(resultado);
    }

    @Operation(summary = "Actualizar un vehículo existente")
    @PutMapping("/{id}")
    public ResponseEntity<VehiculoResponseDto> update(
            @PathVariable Long id,
            @RequestBody VehiculoRequestDto requestDto
    ) {
        return ResponseEntity.ok(vehiculoService.update(id, requestDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "Identificador único del vehiculo", required = true, example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(vehiculoService.delete(id));
    }


    private static class SuccessPageVehiculoResponse extends SuccessMessage<Page<VehiculoResponseDto>> {}

    private static class SuccessVehiculoResponse extends SuccessMessage<VehiculoResponseDto> {}
    private static class SuccessListVehiculoResponse extends SuccessMessage<List<VehiculoResponseDto>> {}
}
