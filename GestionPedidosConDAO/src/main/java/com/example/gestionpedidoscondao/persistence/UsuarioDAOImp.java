package com.example.gestionpedidoscondao.persistence;

import com.example.gestionpedidoscondao.model.Usuario;
import com.example.gestionpedidoscondao.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz UsuarioDAO para interactuar con la base de datos
 * y realizar operaciones CRUD para la entidad Usuario.
 *
 * Esta implementación utiliza JDBC para conectarse y ejecutar las consultas en la base de datos.
 *
 * @author José Miguel Ruiz Guevara
 * @version 1.0
 * @since 1.0
 */
public class UsuarioDAOImp implements UsuarioDAO {

    /**
     * Recupera todos los usuarios de la base de datos y los devuelve como una lista.
     *
     * @return una lista de objetos Usuario que representan todos los usuarios almacenados en la base de datos.
     */
    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                usuarios.add(new Usuario(rs.getInt("id_usuarios"),
                        rs.getString("nombre"),
                        rs.getString("contraseña"),
                        rs.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    /**
     * Inserta un nuevo usuario en la base de datos.
     *
     * @param usuario el objeto Usuario que se va a insertar en la base de datos.
     * @return verdadero si la inserción fue exitosa, falso en caso contrario.
     */
    @Override
    public boolean insert(Usuario usuario) {
        // Implementación del método de inserción.
        return false;
    }

    /**
     * Actualiza los datos de un usuario existente en la base de datos.
     *
     * @param usuario el objeto Usuario que se va a actualizar en la base de datos.
     * @return verdadero si la actualización fue exitosa, falso en caso contrario.
     */
    @Override
    public boolean update(Usuario usuario) {
        // Implementación del método de actualización.
        return false;
    }

    /**
     * Elimina un usuario de la base de datos utilizando su ID.
     *
     * @param id el ID del usuario que se va a eliminar.
     * @return verdadero si la eliminación fue exitosa, falso en caso contrario.
     */
    @Override
    public boolean delete(int id) {
        // Implementación del método de eliminación.
        return false;
    }
}