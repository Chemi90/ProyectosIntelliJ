package com.example.gestionpedidos;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionDB {
    private static Connection connection = null;

    public static Connection obtenerConexion() {
        if (connection != null) {
            return connection;
        } else {
            try {
                Properties propiedades = new Properties();
                InputStream archivoPropiedades = new FileInputStream("C:/dev/WorkSpace/Proyectos IntelliJ/GestionPedidos/src/main/resources/bbdd.cfg");
                propiedades.load(archivoPropiedades);

                String url = propiedades.getProperty("url");
                String user = propiedades.getProperty("user");
                String password = propiedades.getProperty("pass");

                connection = DriverManager.getConnection(url, user, password);
                return connection;
            } catch (SQLException | IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
