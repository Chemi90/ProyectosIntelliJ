package com.example.gestionpedidoscondao.persistence;

import com.example.gestionpedidoscondao.Session;
import com.example.gestionpedidoscondao.model.ItemPedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz {@link ItemPedidoDAO} para interactuar con la base de datos
 * y realizar operaciones CRUD para los {@code ItemPedido}.
 * <p>
 * Esta clase provee la funcionalidad para obtener los detalles de los ítems de un pedido específico
 * a través de la base de datos utilizando JDBC.
 * </p>
 *
 * @author José Miguel Ruiz Guevara
 * @version 1.0
 * @since 1.0
 */
public class ItemPedidoDAOImp implements ItemPedidoDAO{

    /**
     * Busca y devuelve todos los ítems asociados a un código de pedido específico.
     * <p>
     * Esta implementación realiza una consulta a la base de datos para obtener los ítems del pedido,
     * incluyendo el nombre del producto y el precio total calculado en función de la cantidad.
     * </p>
     *
     * @param pedidoId El código único del pedido cuyos ítems se desean recuperar.
     * @return Una lista de {@code ItemPedido} que contiene los ítems del pedido especificado.
     * @throws SQLException Si ocurre algún problema durante la ejecución de la consulta SQL.
     */
    @Override
    public List<ItemPedido> findItemsByPedidoCodigo(String pedidoId) {
        List<ItemPedido> items = new ArrayList<>();

        // Cambia la consulta para que busque por el 'id' del pedido
        String query = "SELECT \n" +
                "    ip.codPedido,\n" +
                "    ip.cantidad,\n" +
                "    p.nombre,\n" +
                "    (ip.cantidad * p.precio) AS precio_total\n" +
                "FROM \n" +
                "    ItemsPedidos AS ip\n" +
                "JOIN \n" +
                "    Productos AS p ON ip.producto = p.id_productos\n" +
                "WHERE \n" +
                "    ip.codPedido = ?;\n";
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, Session.getPedido());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String pedido = rs.getString("CodPedido");
                int cantidad = rs.getInt("cantidad");
                String productoNombre = rs.getString("nombre");
                Double precio = rs.getDouble("precio_total");

                ItemPedido item = new ItemPedido();
                item.setCodPedido(pedido);
                item.setCantidad(cantidad);
                item.setProductoNombre(productoNombre);
                item.setPrecio(precio);

                items.add(item);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
}
