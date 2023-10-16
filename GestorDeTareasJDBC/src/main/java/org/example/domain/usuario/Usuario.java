package org.example.domain.usuario;

import lombok.Data;
import org.example.domain.Database;
import org.example.domain.tarea.Tarea;

import java.util.HashSet;
import java.util.Set;

@Data
public class Usuario extends Database.Usuario {
    private Long id;
    private String nombre;
    private String email;

    private Set<Tarea> tareas = new HashSet<>(0);
}
