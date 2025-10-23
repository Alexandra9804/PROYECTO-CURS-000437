package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.dto.AlquilerCabeceraResponseDto;
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
    public ResponseEntity<List<AlquilerCabeceraResponseDto>> listar() {
        return ResponseEntity.ok(alquilerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlquilerCabeceraResponseDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(alquilerService.findById(id));
    }
}
