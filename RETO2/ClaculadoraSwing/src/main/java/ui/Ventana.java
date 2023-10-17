package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana extends JFrame {
    private JPanel panel1;
    private JTextArea txtNumero1;
    private JTextArea txtNumero2;
    private JButton btnEleccion;
    private JTextArea txtResultado;
    private JLabel info;
    private JComboBox<String> cbEleccion;

    public Ventana() {
        setTitle("Calculadora Básica");
        setSize(600, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());

        txtNumero1 = new JTextArea();
        panel1.add(new JLabel("Número 1:"));
        panel1.add(txtNumero1);

        txtNumero2 = new JTextArea();
        panel1.add(new JLabel("Número 2:"));
        panel1.add(txtNumero2);

        cbEleccion = new JComboBox<>(new String[]{"+", "-", "*", "/"});
        panel1.add(new JLabel("Operación:"));
        panel1.add(cbEleccion);

        txtResultado = new JTextArea();
        panel1.add(new JLabel("Resultado:"));
        panel1.add(txtResultado);

        btnEleccion = new JButton("Calcular");
        panel1.add(btnEleccion);

        info = new JLabel("");
        panel1.add(info);

        btnEleccion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularResultado();
            }
        });

        add(panel1);
    }

    private void calcularResultado() {
        try {
            double num1 = Double.parseDouble(txtNumero1.getText());
            double num2 = Double.parseDouble(txtNumero2.getText());
            String operacion = (String) cbEleccion.getSelectedItem();

            double resultado = 0;

            switch (operacion) {
                case "+":
                    resultado = num1 + num2;
                    break;
                case "-":
                    resultado = num1 - num2;
                    break;
                case "*":
                    resultado = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0)
                        resultado = num1 / num2;
                    else
                        txtResultado.setText("Error: División por cero");
                    break;
                default:
                    txtResultado.setText("Operación no válida");
            }

            txtResultado.setText(String.valueOf(resultado));
        } catch (NumberFormatException ex) {
            txtResultado.setText("Entrada inválida");
        }
    }

    public void mostrar() {
        setVisible(true);
    }

}
