package pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.dto.ClienteRequestDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.dto.ClienteResponseDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.service.ClienteService;

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
                    schema = @Schema(implementation = Void.class))
    )
    @PostMapping
    public ResponseEntity<ClienteResponseDto> registrar(@Valid @RequestBody ClienteRequestDto dto) {
        ClienteResponseDto response = clienteService.save(dto);
        return ResponseEntity.ok(response);
    }
}
