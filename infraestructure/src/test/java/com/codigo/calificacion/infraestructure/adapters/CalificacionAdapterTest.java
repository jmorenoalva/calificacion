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

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
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
    void obtenerCalificacionOut_whenCalificacionExists_returnsCalificacionDTO(){
        //PREPARANDO DATOS PARA EL TEST
        Long id= 1L;
        CalificacionEntity calificacionEntity = new CalificacionEntity();
        Optional<CalificacionEntity> optionalCalificacion = Optional.of(calificacionEntity);
        CalificacionDTO calificacionDTO = new CalificacionDTO();
        //SIMULANDO COMPORTAMIENTO
        when(calificacionRepository.findById(id)).thenReturn(optionalCalificacion);
        when(calificacionMapper.mapTopDto(calificacionEntity)).thenReturn(calificacionDTO);
        //EJECUTANDO
        Optional<CalificacionDTO> result = calificacionAdapter.obtenerCalificacionOut(id);
        //VALIDANDO
        assertTrue(result.isPresent());
        assertEquals(calificacionDTO, result.get());
    }

    @Test
    void obtenerTodosOut_ReturnsListOfCalificacionDTOs() {
        // Arrange
        CalificacionEntity calificacionEntity =getCalificacionEntity();

        List<CalificacionEntity> entityList = Collections.singletonList(calificacionEntity);

        when(calificacionRepository.findAll()).thenReturn(entityList);
        when(calificacionMapper.mapTopDto(calificacionEntity)).thenReturn(new CalificacionDTO());

        // Act
        List<CalificacionDTO> result = calificacionAdapter.obtenerTodosOut();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(calificacionMapper, times(1)).mapTopDto(calificacionEntity);
    }

    @Test
    void actualizarOut_CalificacionExists_UpdatesCalificacion_ReturnsCalificacionDTO() {
        // Arrange
        Long id = 1L;
        CalificacionMapper calificacionMapper1 = new CalificacionMapper();
        CalificacionDTO updatedDTO = new CalificacionDTO();
        CalificacionEntity calificacionEntity = new CalificacionEntity();
        calificacionEntity.setUsuarioUpdate("usuario");
        updatedDTO.setUsuarioUpdate("usuario");

        CalificacionEntity existingEntity = new CalificacionEntity();
        existingEntity.setIdCalificacion(id);
        existingEntity.setUsuarioCreate("usuario");
        existingEntity.setDateCreate(new Timestamp(System.currentTimeMillis()));

        when(calificacionRepository.existsById(id)).thenReturn(true);
        when(calificacionRepository.findById(id)).thenReturn(Optional.of(existingEntity));
        when(calificacionMapper.mapToEntity(updatedDTO)).thenReturn(getCalificacionEntity());
        when(calificacionMapper.mapTopDto(any())).thenReturn(getCalificacionDTO());

        // Act
        CalificacionDTO result = calificacionAdapter.actualizarOut(id, updatedDTO);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getIdCalificacion());
        assertEquals("usuario", result.getUsuarioCreate()); // Verifica si el usuario se actualizó correctamente
        assertNotNull(result.getDateCreate()); // Verifica si la fecha de creación no es nula
        assertNotNull(result.getDateUpdate()); // Verifica si la fecha de actualización no es nula
        assertEquals("usuario", result.getUsuarioUpdate()); // Verifica si el usuario de actualización es correcto
        verify(calificacionRepository, times(1)).save(any());
    }

    @Test
    void deleteOut_CalificacionExists_DeletesCalificacion_ReturnsCalificacionDTOWithStatusInactive() {
        Long id = 1L;
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

    private CalificacionEntity getCalificacionEntity(){
        CalificacionEntity calificacionEntity = new CalificacionEntity();
        calificacionEntity.setIdCalificacion(1L);
        calificacionEntity.setIdUsuario(1L);
        calificacionEntity.setIdVehiculo(1L);
        calificacionEntity.setPuntaje(5);
        calificacionEntity.setComentario("Comentario Test");
        calificacionEntity.setEstado(1);
        calificacionEntity.setUsuarioCreate("usuario");
        calificacionEntity.setDateCreate(new Timestamp(System.currentTimeMillis()));
        return calificacionEntity;
    }

    private CalificacionDTO getCalificacionDTO(){
        CalificacionDTO calificacionDTO = new CalificacionDTO();
        calificacionDTO.setIdCalificacion(1L);
        calificacionDTO.setIdUsuario(1L);
        calificacionDTO.setIdVehiculo(1L);
        calificacionDTO.setPuntaje(5);
        calificacionDTO.setComentario("Comentario Test");
        calificacionDTO.setEstado(1);
        calificacionDTO.setUsuarioCreate("usuario");
        calificacionDTO.setDateCreate(new Timestamp(System.currentTimeMillis()));
        calificacionDTO.setUsuarioUpdate("usuario");
        calificacionDTO.setDateUpdate(new Timestamp(System.currentTimeMillis()));
        return calificacionDTO;
    }
}