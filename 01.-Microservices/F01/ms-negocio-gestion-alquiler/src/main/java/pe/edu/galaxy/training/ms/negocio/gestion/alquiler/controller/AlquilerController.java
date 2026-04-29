package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto.AlquilerRegistroRequestDto;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto.AlquilerRegistroResponseDto;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto.AlquilerResponseDto;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.service.AlquilerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alquileres")
@Tag(name = "Gestión de Alquiler", description = "Operaciones sobre los alquileres")
public class AlquilerController {
    private final AlquilerService alquilerService;

    public AlquilerController(AlquilerService alquilerService){
        this.alquilerService = alquilerService;
    }

    @GetMapping
    public ResponseEntity<List<AlquilerResponseDto>> listar() {
        return ResponseEntity.ok(alquilerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlquilerResponseDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(alquilerService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo alquiler")
    public ResponseEntity<AlquilerRegistroResponseDto> save(
            @Valid @RequestBody AlquilerRegistroRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(alquilerService.save(requestDto));
    }
}
