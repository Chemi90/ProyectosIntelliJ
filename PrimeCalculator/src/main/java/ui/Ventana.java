package ui;

import primos.PrimeCalculator;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Ventana extends JFrame {
    private JPanel main;
    private JTextField txtIni;
    private JTextField txtFin;
    private JButton calcularButton;
    private JList lista;
    private JSpinner spinnerIni;
    private JSpinner spinnerFin;
    private JLabel info;
    private JButton exportar;

    private DefaultListModel<String> datos;

    private ArrayList<Integer> primos = new ArrayList<>();

    public Ventana() {
        this.setContentPane(main);
        this.pack();
        this.setTitle("Calculadora de primos");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        spinnerIni.setModel(new SpinnerNumberModel(1, 1, 1000, 1));
        spinnerFin.setModel(new SpinnerNumberModel(3, 3, 1000, 1));

        datos = new DefaultListModel<>();
        lista.setModel(datos);

        calcularButton.addActionListener((ActionEvent e) -> rellenarPrimos2());
        lista.addListSelectionListener((ListSelectionEvent e) -> mostrarPrimo(e));
        exportar.addActionListener(e -> guardarPrimos());
    }

    private void guardarPrimos() {
        System.out.println("Boton guardar");
        var dialogoGuardar = new JFileChooser();

        //corrobora que hemos elegido guardar de manera correcta
        if (dialogoGuardar.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            //localiza archivo en swing
            File f = dialogoGuardar.getSelectedFile();
            try (var bw = new BufferedWriter(new FileWriter(f))) {
                for (Integer primo : primos) {
                    bw.write(primo + ", ");
                }
                bw.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

    private void mostrarPrimo(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting())
            System.out.println("Evento de lista");
        String primo = (String) lista.getSelectedValue();
        //JOptionPane.showMessageDialog(null, "Numero primo: " + primo, "Informacion", JOptionPane.INFORMATION_MESSAGE);
        info.setText("Numero primo: " + primo);
    }

    /*
    version con spinner
     */
    private void rellenarPrimos2() {
        Integer numero1 = (Integer) spinnerIni.getValue();
        Integer numero2 = (Integer) spinnerFin.getValue();

        if (numero2 > numero1 || numero1 < 1) {
            primos = PrimeCalculator.inRange(numero1, numero2);
            datos.clear();
            if (primos.isEmpty()) datos.addElement("No hay primos.");
            for (Integer n : primos) {
                datos.addElement(n.toString());
            }
            info.setText("Total numeros primos: " + datos.size());
        } else JOptionPane.showMessageDialog(null, "Pon bien el tamaño de los numeros");
    }

    /*
    Version son texto
     */
    private void rellenarPrimos() {
        try {
            Integer numero1 = Integer.valueOf(txtIni.getText());
            Integer numero2 = Integer.valueOf(txtFin.getText());
            if (numero2 > numero1) {
                primos = PrimeCalculator.inRange(numero1, numero2);
                datos.clear();
                if (primos.isEmpty()) datos.addElement("No hay primos.");
                for (Integer n : primos) {
                    datos.addElement(n.toString());
                }
                info.setText("Total numeros primos: " + datos.size());
            } else JOptionPane.showMessageDialog(null, "Pon bien el tamaño de los numeros");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Algo has hecho mal, hazlo de nuevo.");
        }
    }

    public void mostrar() {
        this.setVisible(true);
    }
}
