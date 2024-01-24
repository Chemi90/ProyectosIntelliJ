package com.example.gestionpedidoscondao.persistence;

import com.example.gestionpedidoscondao.Session;
import com.example.gestionpedidoscondao.model.ItemPedido;
import com.example.gestionpedidoscondao.model.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz PedidoDAO para interactuar con la base de datos
 * y recuperar información sobre los pedidos.
 *
 * Esta implementación utiliza una base de datos SQL para realizar las operaciones CRUD.
 *
 * @author José Miguel Ruiz Guevara
 * @version 1.0
 * @since 1.0
 */
public class PedidoDAOImp implements PedidoDAO{

    /**
     * Recupera todos los pedidos asociados a un ID de usuario específico.
     *
     * @param usuarioId el ID del usuario para el cual se buscan los pedidos.
     * @return una lista de objetos {@link Pedido}, cada uno representando un pedido
     *         asociado al usuario proporcionado.
     */
    @Override
    public List<Pedido> findByUsuarioId(int usuarioId) {
        List<Pedido> pedidos = new ArrayList<>();
        String query = "SELECT código, fecha, total FROM Pedidos WHERE usuario = ?";
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, Session.getUsuarioId());
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                String codigo = rs.getString("código");
                Date fecha = rs.getDate("fecha");
                Double total = rs.getDouble("total");

                Pedido pedido = new Pedido();
                pedido.setCódigo(codigo);
                pedido.setFecha(fecha);
                pedido.setTotal(total);


                pedidos.add(pedido);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }
}
