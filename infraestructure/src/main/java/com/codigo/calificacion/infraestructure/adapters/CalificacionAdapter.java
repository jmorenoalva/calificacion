package com.codigo.calificacion.infraestructure.adapters;

import com.codigo.calificacion.domain.aggregates.contants.Constants;
import com.codigo.calificacion.domain.aggregates.dto.CalificacionDTO;
import com.codigo.calificacion.domain.ports.out.CalificacionServiceOut;
import com.codigo.calificacion.infraestructure.entity.CalificacionEntity;
import com.codigo.calificacion.infraestructure.mapper.CalificacionMapper;
import com.codigo.calificacion.infraestructure.repository.CalificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalificacionAdapter implements CalificacionServiceOut {

    private final CalificacionRepository calificacionRepository;
    private final CalificacionMapper calificacionMapper;

    @Override
    public CalificacionDTO crearCalificacionOut(CalificacionDTO calificacionDTO) {
        CalificacionEntity calificacionEntity = calificacionMapper.mapToEntity(calificacionDTO);
        calificacionEntity.setDateCreate(getTimestamp());
        calificacionRepository.save(calificacionEntity);
        return calificacionMapper.mapTopDto(calificacionEntity);
    }

    @Override
    public Optional<CalificacionDTO> obtenerCalificacionOut(Long id) {
        return calificacionRepository.findById(id).map(calificacionMapper::mapTopDto);
    }

    @Override
    public List<CalificacionDTO> obtenerTodosOut() {
        return calificacionRepository.findAll().stream().map(calificacionMapper::mapTopDto).toList();
    }

    @Override
    public CalificacionDTO actualizarOut(Long id, CalificacionDTO calificacionDTO) {
        if (calificacionRepository.existsById(id)) {
            CalificacionEntity calificacionOldEntity = calificacionRepository.findById(id).get();
            CalificacionEntity calificacionEntity = calificacionMapper.mapToEntity(calificacionDTO);

            calificacionEntity.setIdCalificacion(id);
            calificacionEntity.setUsuarioCreate(calificacionOldEntity.getUsuarioCreate());
            calificacionEntity.setDateCreate(calificacionOldEntity.getDateCreate());
            calificacionEntity.setDateUpdate(getTimestamp());
            calificacionEntity.setUsuarioUpdate(calificacionDTO.getUsuarioUpdate());

            calificacionRepository.save(calificacionEntity);
            return calificacionMapper.mapTopDto(calificacionEntity);
        }
        return null;
    }

    @Override
    public CalificacionDTO deleteOut(Long id, String usuario) {
        if (calificacionRepository.existsById(id)){
            Optional<CalificacionEntity> entity=calificacionRepository.findById(id);
            entity.get().setEstado(Constants.STATUS_INACTIVE);
            entity.get().setDateDelete(getTimestamp());
            entity.get().setUsuarioDelete(usuario);
            calificacionRepository.save(entity.get());
            return calificacionMapper.mapTopDto(entity.get());
        }
        return null;
    }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp= new Timestamp(currentTime);
        return timestamp;
    }
}
