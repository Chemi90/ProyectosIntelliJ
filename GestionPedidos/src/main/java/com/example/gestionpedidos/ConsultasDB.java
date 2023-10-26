package com.example.gestionpedidos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultasDB {

    private static Connection connection = ConexionDB.obtenerConexion();
    private static String usuarioLogeado;

    public static boolean verificarCredenciales(String usuario, String contraseña) {
        if (connection == null) {
            return false;
        }

        String consulta = "SELECT * FROM usuarios WHERE nombre = ? AND contraseña = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, contraseña);
            ResultSet resultSet = preparedStatement.executeQuery();
            usuarioLogeado = usuario;

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getUsuarioLogeado() {
        return usuarioLogeado;
    }

    public List<String> getProductNames() {
        List<String> productNames = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT nombre FROM Productos");

            while (resultSet.next()) {
                String productName = resultSet.getString("nombre");
                productNames.add(productName);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productNames;
    }

    public Double getProductPrice(String productName) {
        Double price = null;

        try {
            String sql = "SELECT precio FROM Productos WHERE nombre = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                price = resultSet.getDouble("precio");
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return price;
    }

    public int getProductQuantity(String productName) {
        int quantity = 0;

        try {
            String sql = "SELECT cantidad_disponible FROM Productos WHERE nombre = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                quantity = resultSet.getInt("cantidad_disponible");
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quantity;
    }

    public List<Pedidos> getPedidosDelUsuario(String usuarioLogeado) {
        List<Pedidos> pedidos = new ArrayList<>();

        try {
            String sql = "SELECT código, fecha, total FROM Pedidos WHERE usuario = (SELECT id FROM Usuarios WHERE nombre = ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ConsultasDB.usuarioLogeado);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String codigo = resultSet.getString("código");
                Date fecha = resultSet.getDate("fecha");
                Double total = resultSet.getDouble("total");

                Pedidos pedido = new Pedidos();
                pedido.setCodigoPedido((codigo));
                pedido.setFecha(fecha);
                pedido.setTotal(total);

                pedidos.add(pedido);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidos;
    }
}