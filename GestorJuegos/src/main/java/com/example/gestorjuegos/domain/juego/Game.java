package com.example.gestorjuegos.domain.juego;


import com.example.gestorjuegos.domain.usuario.User;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "coleccionjuegos")
public class Game implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "anio")
    private Long year;
    @Column(name = "num_jugadores")
    private Long players;
    @Column(name = "categoria")
    private String category;
    @Column(name = "plataforma")
    private String platform;
    @Column(name = "estudio")
    private String studio;
    @Column(name = "empresa")
    private String enterprise;
    @Column(name = "formato")
    private String format;
    @Column(name = "estado_juego")
    private String gameStatus;
    @Column(name = "estado_caja")
    private String boxStatus;
    @Column(name = "usuario_id")
    private Long usuarioId;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User user;

}
