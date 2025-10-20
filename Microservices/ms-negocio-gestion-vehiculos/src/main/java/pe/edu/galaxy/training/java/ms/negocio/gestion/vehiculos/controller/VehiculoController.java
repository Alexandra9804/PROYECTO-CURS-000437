package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
        return ResponseEntity.ok(vehiculoService.findById(id).get());

    }

    private static class SuccessVehiculoResponse extends SuccessMessage<VehiculoResponseDto> {}
    private static class SuccessListVehiculoResponse extends SuccessMessage<List<VehiculoResponseDto>> {}
}
