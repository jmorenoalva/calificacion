package com.codigo.calificacion.infraestructure.mapper;

import com.codigo.calificacion.domain.aggregates.dto.CalificacionDTO;
import com.codigo.calificacion.infraestructure.entity.CalificacionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CalificacionMapper {
    private static final ModelMapper modelMapper=new ModelMapper();

    public CalificacionMapper() {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
    }

    public CalificacionDTO mapTopDto(CalificacionEntity calificacionEntity){
        return modelMapper.map(calificacionEntity, CalificacionDTO.class);
    }

    public CalificacionEntity mapToEntity(CalificacionDTO calificacionDTO){
        return modelMapper.map(calificacionDTO, CalificacionEntity.class);
    }
}
