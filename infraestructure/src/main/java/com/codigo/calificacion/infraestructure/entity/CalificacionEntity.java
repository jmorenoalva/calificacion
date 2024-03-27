package com.codigo.calificacion.infraestructure.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "calificacion")
public class CalificacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_calificacion")
    private Long idCalificacion;
    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;
    @Column(name = "id_vehiculo", nullable = false)
    private Long idVehiculo;
    @Column(name = "puntaje", nullable = false)
    private Integer puntaje;
    @Column(name = "comentario", nullable = false)
    private String comentario;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "usuario_create", length = 10)
    private String usuarioCreate;
    @Column(name = "date_create")
    private Timestamp dateCreate;
    @Column(name = "usuario_update", length = 10)
    private String usuarioUpdate;
    @Column(name = "date_update")
    private Timestamp dateUpdate;
    @Column(name = "usuario_delete", length = 10)
    private String usuarioDelete;
    @Column(name = "date_delete")
    private Timestamp dateDelete;
}
