package com.codigo.calificacion.infraestructure.adapters;

import com.codigo.calificacion.domain.aggregates.contants.Constants;
import com.codigo.calificacion.domain.aggregates.dto.CalificacionDTO;
import com.codigo.calificacion.infraestructure.entity.CalificacionEntity;
import com.codigo.calificacion.infraestructure.mapper.CalificacionMapper;
import com.codigo.calificacion.infraestructure.repository.CalificacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalificacionAdapterTest {
    @Mock
    private CalificacionRepository calificacionRepository;

    @Mock
    private CalificacionMapper calificacionMapper;

    @InjectMocks  //a esta implementacion inyectaremos estos mock's
    private CalificacionAdapter calificacionAdapter;

    @BeforeEach //Se inicia antes de que se inicie los test, se inicializan todos los mock's
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void crearCalificacionOut_CreatesCalificacion_ReturnsCalificacionDTO() {
        CalificacionDTO calificacionDTO = new CalificacionDTO();
        CalificacionEntity calificacionEntity = new CalificacionEntity();
        when(calificacionMapper.mapToEntity(calificacionDTO)).thenReturn(calificacionEntity);
        when(calificacionMapper.mapTopDto(calificacionEntity)).thenReturn(calificacionDTO);

        CalificacionDTO result = calificacionAdapter.crearCalificacionOut(calificacionDTO);

        assertNotNull(result);
        assertEquals(calificacionDTO, result);
        verify(calificacionRepository, times(1)).save(calificacionEntity);
    }


    @Test
    void deleteOut_CalificacionExists_DeletesCalificacion_ReturnsCalificacionDTOWithStatusInactive() {
        long id = 1L;
        String usuario = "usuariotest";
        CalificacionEntity calificacionEntity = new CalificacionEntity();
        calificacionEntity.setIdCalificacion(id);
        when(calificacionRepository.existsById(id)).thenReturn(true);
        when(calificacionRepository.findById(id)).thenReturn(Optional.of(calificacionEntity));
        when(calificacionMapper.mapTopDto(calificacionEntity)).thenReturn(new CalificacionDTO());

        CalificacionDTO result = calificacionAdapter.deleteOut(id, usuario);

        assertNotNull(result);
        assertEquals(Constants.STATUS_INACTIVE, calificacionEntity.getEstado());
        assertNotNull(calificacionEntity.getDateDelete());
        assertEquals(usuario, calificacionEntity.getUsuarioDelete());
        verify(calificacionRepository, times(1)).save(calificacionEntity);
    }
}