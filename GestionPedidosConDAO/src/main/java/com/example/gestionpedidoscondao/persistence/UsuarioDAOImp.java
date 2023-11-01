package com.example.gestionpedidoscondao.persistence;

import com.example.gestionpedidoscondao.model.Usuario;
import com.example.gestionpedidoscondao.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImp implements UsuarioDAO {
    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                usuarios.add(new Usuario(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("contraseña"),
                        rs.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
    @Override
    public boolean insert(Usuario usuario) {
        return false;
    }

    @Override
    public boolean update(Usuario usuario) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}