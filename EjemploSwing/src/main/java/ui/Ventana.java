package ui;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana extends JFrame{
    private JButton boton;
    private JPanel panel1;
    private JLabel label;
    private JTextField textField1;

    public Ventana(){
        this.setSize(500, 500);
        this.setTitle("Mi primera ventanita en Swing");
        //para poder o no cambiar el tamaño del a ventana
        this.setResizable(false);
        this.setContentPane(panel1);
        //la ventana se adapta al contenido
        //this.pack();
        //para cerrar al app al cerrar la ventana
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //accion del boton
        boton.addActionListener((e) ->{cambiarTexto(); });
    }
    private void cambiarTexto(){
        label.setText(textField1.getText());
    }
}
