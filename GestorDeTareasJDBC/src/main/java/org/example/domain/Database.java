package org.example.domain;

import lombok.Data;
import org.example.domain.tarea.Tarea;
import org.example.domain.tarea.TareaAdapter;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class Database {

    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    private static Logger logger;


    static{
        logger = Logger.getLogger(Database.class.getName());
        String url = "jdbc:mysql://localhost:3306/Prueba";
        String user = "root";
        String password = "posnose90";
        try {
            connection = DriverManager.getConnection(url, user, password);
            logger.info("Succesful connection to database.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static ArrayList<String[]> getAll(){

        var salida = new ArrayList<String[]>();

        try(Statement st = connection.createStatement()){
            ResultSet rs = st.executeQuery("select * from tarea");

            while(rs.next()){
                String[] fila = convert(rs);
                salida.add(fila);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salida;
    }

    public static ArrayList<Tarea> getAllTarea(){
        var salida = new ArrayList<Tarea>();

        try(Statement st = connection.createStatement()){
            ResultSet rs = st.executeQuery("select * from tarea");

            while(rs.next()){
                salida.add(new TareaAdapter().loadFromResulset(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salida;
    }

    private static String[] convert(ResultSet rs) throws SQLException {
        String[] fila = {
                String.valueOf(rs.getInt("id")),
                rs.getString("titulo"),
                rs.getString("prioridad"),
                String.valueOf(rs.getInt("usuario_id")),
                rs.getString("categoria"),
                rs.getString("descripcion")
        };
        return fila;
    }

    @Data
    public static class Usuario {
        private Long id;
        private String nombre;
        private String email;

        private Set<Tarea> tareas = new HashSet<>(0);
    }
}
