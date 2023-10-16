package org.example.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TareaAdapter {

    private Tarea tarea = new Tarea();

    public TareaAdapter(){}

    public TareaAdapter(Tarea t) {
        this.tarea = t;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public Tarea loadFromResulset(ResultSet rs) throws SQLException {
        tarea.setId(rs.getLong("id"));
        tarea.setTitulo(rs.getString("titulo"));
        tarea.setPrioridad(rs.getString("prioridad"));
        tarea.setCategoria(rs.getString("categoria"));
        tarea.setDescripcion(rs.getString("descripcion"));
        tarea.setUsuario_id(rs.getLong("usuario_id"));
        return tarea;
    }

    public String[] toArrayString() {
        return new String[]{
                String.valueOf(tarea.getId()),
                tarea.getTitulo(),
                tarea.getPrioridad(),
                String.valueOf(tarea.getUsuario_id()),
                tarea.getCategoria(),
                tarea.getDescripcion()};
    }
}
