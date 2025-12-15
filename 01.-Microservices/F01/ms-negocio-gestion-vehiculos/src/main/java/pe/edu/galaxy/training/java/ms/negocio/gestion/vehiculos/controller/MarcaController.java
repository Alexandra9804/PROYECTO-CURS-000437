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
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.MarcaRequestDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.MarcaResponseDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.services.MarcaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/marcas")
@Tag(name = "Gestión de Marcas", description = "Operaciones sobre las marcas")
public class MarcaController {

    private final MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @Operation(
            method = "GET",
            summary = "Lista de marcas registrados",
            description = "API encargada de listar todas las marcas registradas en el sistema. "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista obtenida correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MarcaController.SuccessListMarcaResponse.class))
    )
    @GetMapping
    public ResponseEntity<List<MarcaResponseDto>> listar() {
        List<MarcaResponseDto> lista = marcaService.findAll();
        return ResponseEntity.ok(lista);
    }

    @Operation(
            method = "POST",
            summary = "Registrar una nueva marca",
            description = "API encargada de registrar una nueva marca en el sistema. "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Marca registrada correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MarcaController.SuccessMarcaResponse.class))
    )
    @PostMapping
    public ResponseEntity<MarcaResponseDto> registrar(@Valid @RequestBody MarcaRequestDto dto) {
        MarcaResponseDto response = marcaService.save(dto);
        return ResponseEntity.ok(response);
    }

    @Operation(
            method = "GET",
            summary = "Obtener una marca por su ID",
            description = "API encargada de obtener la información detallada de una marca a partir de su id."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Marca encontrada correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MarcaController.SuccessMarcaResponse.class))
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<MarcaResponseDto> obtenerPorId(
            @Parameter(description = "Identificador único de la marca", required = true, example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(marcaService.findById(id));

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "Identificador único de la marca", required = true, example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(marcaService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaResponseDto> updateCargo(
            @PathVariable Long id,
            @RequestBody MarcaRequestDto marcaRequestDto) {
        return ResponseEntity.ok(marcaService.update(id, marcaRequestDto));
    }

    @Operation(
            method = "GET",
            summary = "Obtener una marca por su ID",
            description = "API encargada de obtener la información detallada de una marca a partir de su id."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Marca encontrada correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MarcaController.SuccessPageMarcaResponse.class))
    )
    @GetMapping("/search")
    public ResponseEntity<Page<MarcaResponseDto>> buscarPorNombre(
            @Parameter(description = "Texto a buscar", example = "Toyota")
            @RequestParam String nombre,

            @Parameter(description = "Número de página (inicia en 0)", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Cantidad de registros por página", example = "10")
            @RequestParam(defaultValue = "5 ") int size
    ) {
        Page<MarcaResponseDto> resultado = marcaService.findByNombre(nombre, page, size);
        return ResponseEntity.ok(resultado);
    }

    private static class SuccessMarcaResponse extends SuccessMessage<MarcaResponseDto> {}
    private static class SuccessListMarcaResponse extends SuccessMessage<List<MarcaResponseDto>> {}
    private static class SuccessPageMarcaResponse extends SuccessMessage<Page<MarcaResponseDto>> {}

}
