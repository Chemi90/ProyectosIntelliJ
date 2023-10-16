package org.example.ui;

import org.example.domain.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.image.DataBufferByte;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class Ventana extends JFrame{
    private JPanel panel1;
    private JTable table1;
    private JLabel info;

    DefaultTableModel data;


    ArrayList<Tarea> tareas = new ArrayList<>();
    public Ventana(){
        this.setContentPane(panel1);
        setSize(600,400);
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

        var dao = new TareaDAOImp(DBConnection.getConnection());
        tareas = dao.loadAllByResponsable(1L);
        fillTable(tareas);
        table1.getSelectionModel().addListSelectionListener(ev-> showDetails(ev));
    }

    private void showDetails(ListSelectionEvent ev) {
        if(!ev.getValueIsAdjusting()){
            Tarea selected = tareas.get(table1.getSelectedRow());
            info.setText(selected.toString());
        }
    }

    private void fillTable(ArrayList<Tarea> tareas) {
        data.setRowCount(0);
        tareas.forEach((t)->{
            data.addRow(new TareaAdapter(t).toArrayString());
        });
        info.setText("Datos cargados correctamente.");
    }

    public void load(){
        setVisible(true);
    }
}
