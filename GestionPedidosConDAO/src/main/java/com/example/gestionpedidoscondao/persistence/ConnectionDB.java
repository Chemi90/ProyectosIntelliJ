package com.example.gestionpedidoscondao.persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDB {
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            Properties propiedades = new Properties();
            InputStream archivoPropiedades = new FileInputStream("C:/dev/WorkSpace/Proyectos IntelliJ/GestionPedidosConDAO/src/main/resources/DB.properties");
            propiedades.load(archivoPropiedades);

            String url = propiedades.getProperty("url");
            String user = propiedades.getProperty("user");
            String password = propiedades.getProperty("pass");

            return DriverManager.getConnection(url, user, password);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
