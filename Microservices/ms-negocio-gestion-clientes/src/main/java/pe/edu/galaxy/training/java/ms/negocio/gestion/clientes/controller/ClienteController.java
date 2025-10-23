package pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.dto.ClienteRequestDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.dto.ClienteResponseDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@Tag(name = "Gestión de Clientes", description = "Operaciones sobre los clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @Operation(
            method = "POST",
            summary = "Registrar un nuevo cliente",
            description = "API encargada de registrar un nuevo cliente en el sistema. "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Cliente registrado correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ClienteResponseDto.class))
    )
    @PostMapping
    public ResponseEntity<ClienteResponseDto> registrar(@Valid @RequestBody ClienteRequestDto dto) {
        ClienteResponseDto response = clienteService.save(dto);
        return ResponseEntity.ok(response);
    }

    @Operation(
            method = "GET",
            summary = "Listar todos los clientes",
            description = "API que devuelve la lista completa de clientes registrados en el sistema."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de clientes obtenida correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ClienteResponseDto.class))
    )
    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> listar() {
        List<ClienteResponseDto> clientes = clienteService.findAll();
        return ResponseEntity.ok(clientes);
    }

    @Operation(
            method = "GET",
            summary = "Obtener cliente por ID",
            description = "API que devuelve los datos del cliente según su identificador único."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Cliente encontrado correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ClienteResponseDto.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Cliente no encontrado",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> obtenerPorId(@PathVariable Long id) {
        ClienteResponseDto cliente = clienteService.findById(id);
        return ResponseEntity.ok(cliente);
    }
}
