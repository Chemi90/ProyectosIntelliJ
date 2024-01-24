package com.example.gestionpedidoscondao.model;

import lombok.*;

/**
 * Clase que representa un usuario dentro del sistema.
 * <p>
 * Esta clase contiene la estructura de datos para almacenar la información de un usuario.
 * Utiliza Lombok para generar constructores, getters y setters automáticamente.
 * </p>
 *
 * @author José Miguel Ruiz Guevara
 * @version 1.0
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario {
    private int id;
    private String nombre;
    private String contraseña;
    private String email;
}
