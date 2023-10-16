package org.example.domain.tarea;

import org.example.domain.usuario.UsuarioDAOImp;

import java.sql.*;
import java.util.ArrayList;

public class TareaDAOImp implements TareaDAO {

    private Connection connection;
    private final static String queryLoad = "select * from tarea where id = ?";
    private final static String queryLoadAll = "SELECT * FROM tarea";
    private final static String queryUpdate = "";
    private final static String queryDelete = "";
    private final static String querySave = "insert into tarea(titulo, prioridad, usuario_id, categoria, descripcion)\n" +
            "values(?, ?, ?, ?, ?)";
    private final static String QueryLoadAllByResponsable = "SELECT * FROM tarea WHERE usuario_id=?";

    public TareaDAOImp(Connection c) {
        connection = c;
    }

    @Override
    public Tarea load(Long id) {
        Tarea salida = null;
        try(var pst = connection.prepareStatement(queryLoad)){
            pst.setLong(1,id);
            var rs = pst.executeQuery();
            if(rs.next()){
                salida = new Tarea();
                salida.setId(rs.getLong("id"));
                salida.setTitulo(rs.getString("titulo"));
                salida.setPrioridad(rs.getString("prioridad"));
                salida.setCategoria(rs.getString("categoria"));
                salida.setDescripcion(rs.getString("descripcion"));
                salida.setUsuario_id(rs.getLong("usuario_id"));

                salida.setUsuario((new UsuarioDAOImp(connection)).load(salida.getUsuario_id()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salida;
    }

    @Override
    public ArrayList<Tarea> loadAll() {
        var salida = new ArrayList<Tarea>();

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(queryLoadAll);

            while (rs.next()) {
                Tarea t = (new TareaAdapter().loadFromResulset(rs));
                t.setUsuario((new UsuarioDAOImp(connection)).load(t.getUsuario_id()));
                salida.add(t);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salida;
    }

    @Override
    public ArrayList<Tarea> loadAllByResponsable(Long responsable) {
        var salida = new ArrayList<Tarea>();
        try (PreparedStatement pst = connection.prepareStatement(QueryLoadAllByResponsable)) {
            pst.setLong(1, responsable);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                salida.add(new TareaAdapter().loadFromResulset(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salida;
    }

    @Override
    public Tarea save(Tarea t) {
        return null;
    }

    @Override
    public Tarea update(Tarea t) {
        return null;
    }

    @Override
    public void remove(Tarea t) {

    }
}
