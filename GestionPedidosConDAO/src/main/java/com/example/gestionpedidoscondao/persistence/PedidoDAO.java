package com.example.gestionpedidoscondao.persistence;

import com.example.gestionpedidoscondao.model.Pedido;

import java.util.List;


/**
 * Interfaz para el acceso a datos relacionados con los pedidos.
 * Define las operaciones que pueden ser realizadas sobre los objetos de tipo {@code Pedido}
 * en la persistencia de la aplicación.
 *
 * Los métodos dentro de esta interfaz son responsables de interactuar con la base de datos
 * para realizar operaciones CRUD relacionadas con los pedidos.
 *
 * @author José Miguel Ruiz Guevara
 * @version 1.0
 * @since 1.0
 */
public interface PedidoDAO {

    /**
     * Encuentra todos los pedidos asociados con un identificador de usuario específico.
     *
     * @param usuarioId El identificador único del usuario cuyos pedidos se quieren recuperar.
     * @return Una lista de {@code Pedido}, que representa todos los pedidos hechos por el usuario.
     */
    List<Pedido> findByUsuarioId(int usuarioId);
}
