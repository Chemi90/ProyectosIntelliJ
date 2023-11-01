package com.example.gestionpedidos.persistence;

import com.example.gestionpedidos.model.Carrito;
import com.example.gestionpedidos.model.ItemPedido;
import com.example.gestionpedidos.model.Pedidos;

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

    public static Integer getUsuarioIdLogeado() {
        try {
            String consulta = "SELECT id FROM usuarios WHERE nombre = ?";
            PreparedStatement stmt = connection.prepareStatement(consulta);
            stmt.setString(1, getUsuarioLogeado());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insertarElementosCarrito(int pedidoID, List<Carrito> itemsCarrito) {
        if (connection == null || itemsCarrito == null || itemsCarrito.isEmpty()) {
            return false;
        }

        try {
            // Insertar elementos del carrito en la tabla "ItemsPedidos"
            String sqlItems = "INSERT INTO ItemsPedidos (pedido, cantidad, producto) VALUES (?, ?, ?)";
            PreparedStatement preparedStatementItems = connection.prepareStatement(sqlItems);

            for (Carrito item : itemsCarrito) {
                preparedStatementItems.setInt(1, pedidoID);
                preparedStatementItems.setInt(2, item.getCantidad());
                int productoID = getProductID(item.getNombre()); // Obtener el ID del producto por nombre
                preparedStatementItems.setInt(3, productoID);
                preparedStatementItems.addBatch();
            }

            int[] rowsAffectedItems = preparedStatementItems.executeBatch();

            for (int rows : rowsAffectedItems) {
                if (rows == PreparedStatement.EXECUTE_FAILED) {
                    throw new SQLException("Error al insertar elementos del carrito.");
                }
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean insertarPedido(String codigoPedido, Date fechaPedido, Integer usuario, double totalCompra, List<Carrito> itemsCarrito) {
        if (connection == null || itemsCarrito == null || itemsCarrito.isEmpty()) {
            return false;
        }

        try {
            // Establecer autocommit en falso
            connection.setAutoCommit(false);

            // Insertar el pedido en la tabla "Pedidos" y obtener el ID generado automáticamente
            String sqlPedido = "INSERT INTO Pedidos (código, fecha, usuario, total) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatementPedido = connection.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);
            preparedStatementPedido.setString(1, codigoPedido);
            preparedStatementPedido.setDate(2, fechaPedido);
            preparedStatementPedido.setInt(3, usuario);
            preparedStatementPedido.setDouble(4, totalCompra);

            int rowsAffectedPedido = preparedStatementPedido.executeUpdate();

            if (rowsAffectedPedido > 0) {
                // Obtener el ID del pedido generado automáticamente
                ResultSet generatedKeys = preparedStatementPedido.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int pedidoID = generatedKeys.getInt(1);

                    // Insertar elementos del carrito en la tabla "ItemsPedidos"
                    String sqlItems = "INSERT INTO ItemsPedidos (pedido, cantidad, producto) VALUES (?, ?, ?)";
                    PreparedStatement preparedStatementItems = connection.prepareStatement(sqlItems);

                    for (Carrito item : itemsCarrito) {
                        preparedStatementItems.setInt(1, pedidoID);
                        preparedStatementItems.setInt(2, item.getCantidad());
                        int productoID = getProductID(item.getNombre()); // Obtener el ID del producto por nombre
                        preparedStatementItems.setInt(3, productoID);
                        preparedStatementItems.addBatch();
                    }

                    int[] rowsAffectedItems = preparedStatementItems.executeBatch();

                    for (int rows : rowsAffectedItems) {
                        if (rows == PreparedStatement.EXECUTE_FAILED) {
                            throw new SQLException("Error al insertar elementos del carrito.");
                        }
                    }

                    // Confirmar la transacción
                    connection.commit();

                    // Limpiar el carrito
                    itemsCarrito.clear();
                    return true;
                }
            } else {
                // Error al insertar el pedido
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Restablecer autocommit a verdadero
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
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

    public int getProductID(String productName) {
        int productID = -1; // Valor predeterminado en caso de error o no encontrar el producto

        try {
            // Prepara la consulta SQL con una cláusula WHERE para buscar por nombre
            String query = "SELECT id FROM Productos WHERE nombre = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, productName); // Asigna el nombre del producto

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                productID = resultSet.getInt("id");
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productID;
    }

    public int getPedidoID(String codigoPedido) {
        int pedidoID = -1; // Valor predeterminado en caso de error o no encontrar el pedido

        try {
            String sql = "SELECT id FROM Pedidos WHERE código = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, codigoPedido);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                pedidoID = resultSet.getInt("id");
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidoID;
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

    public static List<ItemPedido> getElementosPedido(String codigoPedido) {
        List<ItemPedido> elementosPedido = new ArrayList<>();

        try {
            String sql = "SELECT p.id_items, p.pedido, p.cantidad, p.producto, pr.nombre AS nombre_producto " +
                    "FROM ItemsPedidos p " +
                    "INNER JOIN Productos pr ON p.producto = pr.id_productos " +
                    "WHERE p.pedido = (SELECT id_pedidos FROM Pedidos WHERE código = ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, codigoPedido);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idItems = resultSet.getInt("id_items");
                int cantidad = resultSet.getInt("cantidad");
                int idProducto = resultSet.getInt("producto");
                String nombreProducto = resultSet.getString("nombre_producto");

                ItemPedido elementoPedido = new ItemPedido(idItems, codigoPedido, cantidad, idProducto, nombreProducto);
                elementosPedido.add(elementoPedido);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return elementosPedido;
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