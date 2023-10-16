package org.example.domain;

import org.example.Main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class DBConnection {

    private static final Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    private static Logger logger;


    static {
        logger = Logger.getLogger(Database.class.getName());
        String url;
        String user;
        String password;

        var cfg = new Properties();
        try {
            cfg.load(Main.class.getClassLoader().getResourceAsStream("ddbb.cfg"));
            logger.info("configuracion cargada");
            url = "jdbc:mysql://" + cfg.getProperty("host") + ":" + cfg.getProperty("port") + "/" + cfg.getProperty("dbname");
            logger.info(url);
            user = cfg.getProperty("user");
            password = cfg.getProperty("pass");
        } catch (IOException e) {
            logger.severe("Error procesando configuracion.");
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(url, user, password);
            logger.info("Succesful connection to database.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
