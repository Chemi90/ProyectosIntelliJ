package com.example.gestorjuegos.domain.usuario;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name="user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="usuario")
    private String username;
    @Column(name="contraseña")
    private String password;
}
