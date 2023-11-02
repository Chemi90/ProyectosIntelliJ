package com.example.gestionpedidoscondao.persistence;

import com.example.gestionpedidoscondao.model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImp implements ProductoDAO{
    @Override
    public List<Producto> findAll() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM Productos";
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("id_productos"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("cantidad_disponible")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
}
