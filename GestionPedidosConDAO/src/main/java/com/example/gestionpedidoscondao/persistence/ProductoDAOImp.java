package com.example.gestionpedidoscondao.persistence;

import com.example.gestionpedidoscondao.model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz ProductoDAO para realizar operaciones CRUD
 * sobre la entidad Producto en la base de datos.
 *
 * Utiliza consultas SQL para interactuar con la base de datos y mapea los
 * resultados a objetos de tipo Producto.
 *
 * @author José Miguel Ruiz Guevara
 * @version 1.0
 * @since 1.0
 */
public class ProductoDAOImp implements ProductoDAO{

    /**
     * Recupera todos los productos registrados en la base de datos.
     *
     * @return una lista de objetos Producto que representa todos los productos disponibles.
     */
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
