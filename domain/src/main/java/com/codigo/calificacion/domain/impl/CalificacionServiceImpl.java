package com.codigo.calificacion.domain.impl;

import com.codigo.calificacion.domain.aggregates.dto.CalificacionDTO;
import com.codigo.calificacion.domain.ports.in.CalificacionServiceIn;
import com.codigo.calificacion.domain.ports.out.CalificacionServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<CalificacionDTO> calificacionDTO = calificacionServiceOut.obtenerCalificacionOut(id);
        if (calificacionDTO.isEmpty()) {
            throw new IllegalArgumentException("Id invalido");
        }
        return calificacionDTO.get();
    }

    @Override
    public List<CalificacionDTO> obtenerTodosIn() {
        return calificacionServiceOut.obtenerTodosOut();
    }

    @Override
    public CalificacionDTO actualizarIn(Long id, CalificacionDTO calificacionDTO) {
        return calificacionServiceOut.actualizarOut(id, calificacionDTO);
    }

    @Override
    public CalificacionDTO deleteIn(Long id, String usuario) {
        return calificacionServiceOut.deleteOut(id,usuario);
    }
}
