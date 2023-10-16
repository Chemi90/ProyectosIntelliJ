package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Principal extends JFrame{
    private JPanel panel;
    private JTable table;
    private JTextField txtTarea;
    private JComboBox comboBox1;
    private JTextField txtEstado;
    private JTextArea txtDescripcion;
    private JButton añadirButton;
    private JLabel Descripcion;
    private JLabel info;

    private DefaultTableModel datos;

    public Principal() {
        this.setContentPane(panel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500,300);
        this.setTitle("Listado de tareas");
        //setResizable(false);
        setLocationRelativeTo(null);

        datos = (DefaultTableModel) table.getModel();
        datos.addColumn("Tarea");
        datos.addColumn("Prioridad");
        datos.addColumn("Estado");
        datos.addColumn("Descripcion");
        añadirButton.addActionListener(e -> addToTable());
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                readTable();
            }
        });
    }

    private void readTable() {
        int rowCount = table.getRowCount();

        // Verificar si hay filas en la tabla
        if (rowCount > 0) {
            int selected = table.getSelectedRow();

            // Verificar si hay una fila seleccionada
            if (selected >= 0) {
                System.out.println("Fila seleccionada: " + selected);
                System.out.println("Tarea: " + datos.getValueAt(selected, 0));
                System.out.println("Prioridad: " + datos.getValueAt(selected, 1));
                System.out.println("Estado: " + datos.getValueAt(selected, 2));
                System.out.println("Descripción: " + datos.getValueAt(selected, 3));
            } else {
                System.out.println("No hay fila seleccionada.");
            }
        } else {
            System.out.println("No hay filas en la tabla.");
        }
    }

    private void addToTable(){
        String tarea = txtTarea.getText();
        String prioridad = (String) comboBox1.getSelectedItem();
        String estado = txtEstado.getText();
        String descripcion = txtDescripcion.getText();

        System.out.println(tarea);
        System.out.println(prioridad);
        System.out.println(estado);
        System.out.println(descripcion);

        String[] dato = {tarea, prioridad, estado, descripcion};
        datos.addRow(dato);
    }

    public void load(){
        setVisible(true);
    }
}
