package com.example.diseniorecetariococina.models;

import javafx.fxml.Initializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receta {
    private String nombre;
    private String tipo;
    private Integer duracion;
    private String dificultad;
}
