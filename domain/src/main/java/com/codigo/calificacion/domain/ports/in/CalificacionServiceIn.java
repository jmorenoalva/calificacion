package com.codigo.calificacion.domain.ports.in;

import com.codigo.calificacion.domain.aggregates.dto.CalificacionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CalificacionServiceIn {
    CalificacionDTO crearCalificacionIn(CalificacionDTO calificacionDTO);
    CalificacionDTO obtenerCalificacionIn(Long id);
    List<CalificacionDTO> obtenerTodosIn();
    CalificacionDTO actualizarIn(Long id, CalificacionDTO calificacionDTO);
    CalificacionDTO deleteIn(Long id, String usuario);
}
