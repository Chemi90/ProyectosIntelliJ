package com.example.gestionpedidoscondao.persistence;

import com.example.gestionpedidoscondao.model.ItemPedido;

import java.util.List;

/**
 * Interfaz para el objeto de acceso a datos que maneja operaciones relacionadas con las entidades {@code ItemPedido}.
 * <p>
 * Define una API abstracta que realiza operaciones CRUD para las entidades {@code ItemPedido}.
 * Las implementaciones de esta interfaz se encargarán de las interacciones reales con la base de datos.
 * </p>
 *
 * @author José Miguel Ruiz Guevara
 * @version 1.0
 * @since 1.0
 */
public interface ItemPedidoDAO {

    /**
     * Recupera una lista de objetos {@code ItemPedido} asociados con un código de pedido específico.
     * <p>
     * Las implementaciones de este método retornarán los ítems pertenecientes al pedido identificado
     * por el {@code pedidoId} dado. Esto típicamente implica una consulta a la base de datos utilizando
     * JDBC u otro marco de persistencia.
     * </p>
     *
     * @param pedidoId el identificador único del pedido cuyos ítems se desean recuperar
     * @return una {@code List} de objetos {@code ItemPedido}
     */
    List<ItemPedido> findItemsByPedidoCodigo(String pedidoId);
}