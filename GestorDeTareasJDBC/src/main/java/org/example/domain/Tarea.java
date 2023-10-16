package org.example.domain;

import lombok.Data;

@Data
public class Tarea {
    private Long id;
    private String titulo;
    private String prioridad;
    private Long usuario_id;
    private String categoria;
    private String descripcion;


}


