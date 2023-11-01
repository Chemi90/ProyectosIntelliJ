package com.example.gestionpedidoscondao;

import lombok.Getter;

public class Session {
    @Getter
    private static String usuarioLogeado;
    @Getter
    private static int usuarioId;

    public static void setUsuarioLogeado(String usuario) {
        usuarioLogeado = usuario;
    }

    public static void setUsuarioId(int id) {
        usuarioId = id;
    }
}

