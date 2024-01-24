package com.example.gestionpedidoscondao.persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase de utilidad para obtener una instancia de conexión a la base de datos.
 * <p>
 * Esta clase provee un método para establecer una conexión con la base de datos
 * utilizando las credenciales y la URL de conexión especificadas en un archivo de propiedades.
 * </p>
 *
 * @author José Miguel Ruiz Guevara
 * @version 1.0
 * @since 1.0
 */
public class ConnectionDB {
    private static Connection connection = null;

    /**
     * Obtiene una instancia de {@link Connection} para operaciones en la base de datos.
     * <p>
     * Los parámetros de conexión son extraídos de un archivo de propiedades ubicado en
     * el directorio de recursos. Este método asegura que la conexión con la
     * base de datos se establezca utilizando estos parámetros y el driver JDBC.
     * </p>
     *
     * @return un objeto {@link Connection} a la base de datos o {@code null} si la conexión no se puede establecer
     */
    public static Connection getConnection() {
        try {
            Properties propiedades = new Properties();
            InputStream archivoPropiedades = new FileInputStream("src/main/resources/DB.properties");
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
