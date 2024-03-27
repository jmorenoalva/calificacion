package com.codigo.calificacion.domain.impl;

import com.codigo.calificacion.domain.aggregates.dto.CalificacionDTO;
import com.codigo.calificacion.domain.ports.in.CalificacionServiceIn;
import com.codigo.calificacion.domain.ports.out.CalificacionServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalificacionServiceImpl implements CalificacionServiceIn {

    private final CalificacionServiceOut calificacionServiceOut;
    @Override
    public CalificacionDTO crearCalificacionIn(CalificacionDTO calificacionDTO) {
        return calificacionServiceOut.crearCalificacionOut(calificacionDTO);
    }

    @Override
    public CalificacionDTO obtenerCalificacionIn(Long id) {
        return null;
    }

    @Override
    public List<CalificacionDTO> obtenerTodosIn() {
        return null;
    }

    @Override
    public CalificacionDTO actualizarIn(Long id, CalificacionDTO calificacionDTO) {
        return null;
    }

    @Override
    public CalificacionDTO deleteIn(Long id, String usuario) {
        return null;
    }
}
