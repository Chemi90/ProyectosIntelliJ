package com.example.gestionpedidoscondao.persistence;

import com.example.gestionpedidoscondao.model.ItemPedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemPedidoDAOImp implements ItemPedidoDAO{
    @Override
    public List<ItemPedido> findItemsByPedidoId(int pedidoId) {
        List<ItemPedido> items = new ArrayList<>();

        // Cambia la consulta para que busque por el 'id' del pedido
        String query = "SELECT * FROM ItemsPedidos WHERE pedido = ?";
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, pedidoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int pedido = rs.getInt("pedidoId");
                int cantidad = rs.getInt("cantidad");
                int productoId = rs.getInt("producto");

                ItemPedido item = new ItemPedido();
                item.setId(id);
                item.setPedidoId(pedido);
                item.setCantidad(cantidad);
                item.setProductoId(productoId);

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
