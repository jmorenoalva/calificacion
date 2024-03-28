package com.codigo.calificacion.infraestructure.repository;

import com.codigo.calificacion.infraestructure.entity.CalificacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CalificacionRepository extends JpaRepository<CalificacionEntity, Long> {
    List<CalificacionEntity> findByEstado(@Param("estado") Integer estado);
}
