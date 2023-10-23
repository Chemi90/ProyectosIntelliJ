package org.example.ui;

import org.example.domain.DBConnection;
import org.example.domain.tarea.Tarea;
import org.example.domain.tarea.TareaAdapter;
import org.example.domain.tarea.TareaDAOImp;
import org.example.domain.usuario.Usuario;
import org.example.domain.usuario.UsuarioDAOImp;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Ventana extends JFrame {
    private final TareaDAOImp daoTarea = new TareaDAOImp(DBConnection.getConnection());
    DefaultTableModel data;
    ArrayList<Tarea> tareas = new ArrayList<>();
    private JPanel panel1;
    private JTable table1;
    private JLabel info;
    private JButton button1;
    private JTextField textFieldTarea;
    private JTextField textFieldDescripcion;
    private JComboBox comboBoxPrioridad;
    private JComboBox comboBoxCategoria;

    public Ventana() {
        this.setContentPane(panel1);
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Acceso con JDBC");

        table1.setRowHeight(40);
        data = (DefaultTableModel) table1.getModel();
        data.addColumn("id");
        data.addColumn("tarea");
        data.addColumn("prioridad");
        data.addColumn("usuario_id");
        data.addColumn("categoria");
        data.addColumn("descripcion");

        //var datos = Database.getAll();

        //datos.forEach((e)->data.addRow(e));
        //table1.doLayout();

        tareas = daoTarea.loadAll();
        fillTable(tareas);
        table1.getSelectionModel().addListSelectionListener(ev -> showDetails(ev));

        button1.addActionListener(e -> guardarTarea());
    }

    private void guardarTarea() {
        Tarea t = new Tarea();
        Usuario u = (new UsuarioDAOImp(DBConnection.getConnection())).load(1L);
        t.setUsuario(u);
        t.setUsuario_id(u.getId());
        t.setCategoria((String) comboBoxCategoria.getSelectedItem());
        t.setDescripcion(textFieldDescripcion.getText());
        t.setTitulo(textFieldTarea.getText());
        t.setPrioridad((String) comboBoxPrioridad.getSelectedItem());
        t = (daoTarea).save(t);

        tareas = daoTarea.loadAll();
        fillTable(tareas);

        textFieldDescripcion.setText("");
        textFieldTarea.setText("");
    }

    private void showDetails(ListSelectionEvent ev) {
        if (!ev.getValueIsAdjusting()) {
            if(table1.getSelectedRow()>=0) {
                Tarea selected = tareas.get(table1.getSelectedRow());
                info.setText(selected.toString());
            }
        }
    }

    private void fillTable(ArrayList<Tarea> tareas) {
        data.setRowCount(0);
        tareas.forEach((t) -> {
            data.addRow(new TareaAdapter(t).toArrayString());
        });
        info.setText("Datos cargados correctamente.");
    }

    public void load() {
        setVisible(true);
    }
}
