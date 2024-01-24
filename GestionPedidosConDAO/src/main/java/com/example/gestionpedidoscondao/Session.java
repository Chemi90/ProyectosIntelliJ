package com.example.gestionpedidoscondao;

import lombok.Getter;

/**
 * Clase <code>Session</code> que maneja la información de la sesión del usuario.
 * Mantiene los detalles del usuario logueado y del pedido actual en el contexto de la aplicación.
 * Utiliza anotaciones de Lombok para generar automáticamente los métodos 'getters'.
 * Es importante notar que al ser una clase con miembros estáticos, se comporta de manera global,
 * manteniendo una única instancia de los datos para toda la aplicación.
 *
 * @author José Miguel Ruiz Guevara
 * @version 1.0
 */
public class Session {

    @Getter
    private static String usuarioLogeado; // Usuario actualmente logueado en la sesión

    @Getter
    private static int usuarioId; // ID del usuario logueado

    @Getter
    private static String pedido; // Pedido actual en la sesión

    /**
     * Establece el nombre de usuario logueado en la sesión.
     *
     * @param usuario El nombre de usuario a establecer en la sesión.
     */
    public static void setUsuarioLogeado(String usuario) {
        usuarioLogeado = usuario;
    }

    /**
     * Establece el ID del usuario logueado en la sesión.
     *
     * @param id El identificador del usuario a establecer en la sesión.
     */
    public static void setUsuarioId(int id) {
        usuarioId = id;
    }

    /**
     * Establece el pedido en la sesión.
     *
     * @param pedido El pedido a establecer en la sesión.
     */
    public static void setPedido(String pedido) {
        Session.pedido = pedido;
    }
}

