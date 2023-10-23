package org.example.domain.tarea;

import lombok.extern.java.Log;
import org.example.domain.usuario.UsuarioDAOImp;

import java.sql.*;
import java.util.ArrayList;

@Log
public class TareaDAOImp implements TareaDAO {

    private final static String queryLoad = "select * from tarea where id = ?";
    private final static String queryLoadAll = "SELECT * FROM tarea";
    private final static String queryUpdate = "upadte tarea(titulo, prioridad, usuario_id, categoria, descripcion) \"+\n"+
            "                \"VALUES(?,?,?,?,?) WHERE id=?";
    private final static String queryDelete = "";
    private final static String querySave = "insert into tarea(titulo, prioridad, usuario_id, categoria, descripcion)\n" +
            "values(?, ?, ?, ?, ?)";
    private final static String QueryLoadAllByResponsable = "SELECT * FROM tarea WHERE usuario_id=?";
    private Connection connection;

    public TareaDAOImp(Connection c) {
        connection = c;
    }

    @Override
    public Tarea load(Long id) {
        Tarea salida = null;
        try (var pst = connection.prepareStatement(queryLoad)) {
            pst.setLong(1, id);
            var rs = pst.executeQuery();
            if (rs.next()) {
                salida = new Tarea();
                salida.setId(rs.getLong("id"));
                salida.setTitulo(rs.getString("titulo"));
                salida.setPrioridad(rs.getString("prioridad"));
                salida.setCategoria(rs.getString("categoria"));
                salida.setDescripcion(rs.getString("descripcion"));

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

        Tarea salida = null;

        if (t.getUsuario() != null) {

            try (PreparedStatement pst = connection.prepareStatement(querySave, Statement.RETURN_GENERATED_KEYS)) {
                log.info(querySave);
                log.info(t.toString());
                pst.setString(1, t.getTitulo());
                pst.setString(2, t.getPrioridad());
                pst.setLong(3, t.getUsuario().getId());
                pst.setString(4, t.getCategoria());
                pst.setString(5, t.getDescripcion());

                pst.addBatch();

                int result = pst.executeUpdate();

                if (result > 0) {
                    ResultSet rs = pst.getGeneratedKeys();
                    rs.next();
                    salida = t;
                    salida.setId(rs.getLong(1));
                    log.info("Tarea insertada: " + salida.getId());
                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return salida;
    }

    @Override
    public Tarea update(Tarea t) {
        return null;
    }

    @Override
    public void remove(Tarea t) {

    }
}
