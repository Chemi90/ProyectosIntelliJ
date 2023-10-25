package com.example.gestionpedidos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultasDB {
    public static boolean verificarCredenciales(String usuario, String contraseña) {
        Connection connection = ConexionDB.obtenerConexion();
        if (connection == null) {
            return false;
        }

        String consulta = "SELECT * FROM usuarios WHERE nombre = ? AND contraseña = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, contraseña);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}