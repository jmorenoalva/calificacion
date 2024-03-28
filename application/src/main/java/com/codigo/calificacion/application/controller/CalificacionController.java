package com.codigo.calificacion.application.controller;

import com.codigo.calificacion.domain.aggregates.dto.CalificacionDTO;
import com.codigo.calificacion.domain.ports.in.CalificacionServiceIn;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title="API-CALIFICACION",
                version = "1.0.0",
                description = "Mantenimiento de calificaciones"
        )
)
@RestController
@RequestMapping("/v1/calificacion")
@RequiredArgsConstructor
public class CalificacionController {
    private final CalificacionServiceIn calificacionServiceIn;

    @Operation(summary = "Api para crear una calificacion")
    @PostMapping
    public ResponseEntity<CalificacionDTO> registrar(@RequestBody CalificacionDTO calificacionDTO){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(calificacionServiceIn.crearCalificacionIn(calificacionDTO));
    }

    @Operation(summary = "Api para obtener una calificacion")
    @GetMapping("/{id}")
    public ResponseEntity<CalificacionDTO> obtenerCalificacion(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(calificacionServiceIn.obtenerCalificacionIn(id));
    }

    @Operation(summary = "Api para obtener todas las calificaciones")
    @GetMapping()
    public ResponseEntity<List<CalificacionDTO>> obtenerTodos(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(calificacionServiceIn.obtenerTodosIn());
    }

    @Operation(summary = "Api para actualizar los datos de una calificacion")
    @PutMapping("/{id}")
    public ResponseEntity<CalificacionDTO> actualizar(@PathVariable Long id, @RequestBody CalificacionDTO calificacionDTO){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(calificacionServiceIn.actualizarIn(id, calificacionDTO));
    }

    @Operation(summary = "Api para borrar datos de una calificacion, eliminacion logica")
    @DeleteMapping("/{id}")
    public ResponseEntity<CalificacionDTO> eliminar(@PathVariable Long id, @RequestHeader("usuario") String usuario){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(calificacionServiceIn.deleteIn(id, usuario));
    }
}
