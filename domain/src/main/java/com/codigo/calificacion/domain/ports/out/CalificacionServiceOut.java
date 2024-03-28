package com.codigo.calificacion.domain.ports.out;

import com.codigo.calificacion.domain.aggregates.dto.CalificacionDTO;

import java.util.List;
import java.util.Optional;

public interface CalificacionServiceOut {
    CalificacionDTO crearCalificacionOut(CalificacionDTO calificacionDTO);
    Optional<CalificacionDTO> obtenerCalificacionOut(Long id);
    List<CalificacionDTO> obtenerTodosOut();
    CalificacionDTO actualizarOut(Long id, CalificacionDTO calificacionDTO);
    CalificacionDTO deleteOut(Long id, String usuario);
}
