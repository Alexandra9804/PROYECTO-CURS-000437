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
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.ModeloRequestDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.dto.ModeloResponseDto;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.services.ModeloService;

@RestController
@RequestMapping("/api/v1/modelos")
@Tag(name = "Gestión de Modelos", description = "Operaciones sobre los modelos")
public class ModeloController {

    private final ModeloService modeloService;

    public ModeloController(final ModeloService modeloService) {
        this.modeloService = modeloService;
    }

    @Operation(
            method = "GET",
            summary = "Listar modelos filtrando opcionalmente por nombre y/o marca",
            description = "API encargada de listar los modelos de vehículos aplicando filtros opcionales por nombre del modelo y por identificador de marca. Los resultados están paginados."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Listado obtenido correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ModeloController.SuccessPageModeloResponse.class))
    )
    @GetMapping("/search")
    public ResponseEntity<Page<ModeloResponseDto>> buscarModelos(
            @Parameter(description = "Texto para filtrar por nombre del modelo (opcional)", example = "Civic")
            @RequestParam(required = false) String nombre,

            @Parameter(description = "ID de la marca para filtrar modelos (opcional)", example = "1")
            @RequestParam(required = false) Long idMarca,

            @Parameter(description = "Número de página (inicia en 0)", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Cantidad de registros por página", example = "10")
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<ModeloResponseDto> resultado = modeloService.listarModelos(nombre, idMarca, page, size);
        return ResponseEntity.ok(resultado);
    }

    @Operation(
            method = "POST",
            summary = "Registrar un nuevo modelo",
            description = "API encargada de registrar un nuevo modelo de vehículo en el sistema."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Modelo registrado correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ModeloController.SuccessModeloResponse.class))
    )
    @PostMapping
    public ResponseEntity<ModeloResponseDto> registrar(
            @Valid @RequestBody ModeloRequestDto dto
    ) {
        ModeloResponseDto response = modeloService.save(dto);
        return ResponseEntity.ok(response);
    }

    private static class SuccessModeloResponse extends SuccessMessage<ModeloResponseDto> {}

    private static class SuccessPageModeloResponse extends SuccessMessage<Page<ModeloResponseDto>> {}

}
