package com.codigo.calificacion.domain.aggregates.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CalificacionDTO {
    private Long idCalificacion;
    private Long idUsuario;
    private Long idVehiculo;
    private Integer puntaje;
    private String comentario;
    private Integer estado;
    private String usuarioCreate;
    private Timestamp dateCreate;
    private String usuarioUpdate;
    private Timestamp dateUpdate;
    private String usuarioDelete;
    private Timestamp dateDelete;
}
