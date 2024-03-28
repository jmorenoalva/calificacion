package com.codigo.calificacion.application.controller;

import com.codigo.calificacion.domain.aggregates.dto.CalificacionDTO;
import com.codigo.calificacion.domain.ports.in.CalificacionServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/calificacion")
@RequiredArgsConstructor
public class CalificacionController {
    private final CalificacionServiceIn calificacionServiceIn;

    @PostMapping
    public ResponseEntity<CalificacionDTO> registrar(@RequestBody CalificacionDTO calificacionDTO){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(calificacionServiceIn.crearCalificacionIn(calificacionDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalificacionDTO> obtenerCalificacion(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(calificacionServiceIn.obtenerCalificacionIn(id));
    }

    @GetMapping()
    public ResponseEntity<List<CalificacionDTO>> obtenerTodos(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(calificacionServiceIn.obtenerTodosIn());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalificacionDTO> actualizar(@PathVariable Long id, @RequestBody CalificacionDTO calificacionDTO){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(calificacionServiceIn.actualizarIn(id, calificacionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CalificacionDTO> eliminar(@PathVariable Long id, @RequestHeader("usuario") String usuario){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(calificacionServiceIn.deleteIn(id, usuario));
    }
}
