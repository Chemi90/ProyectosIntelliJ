package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Login extends JFrame{
    private JTextField txtUsuario;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JPanel panel1;
    private JLabel info;

    public Login() {
        this.setContentPane(panel1);
        this.setSize(300,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setResizable(false);
        this.setTitle("Login");
        this.setLocationRelativeTo(null);//para que la ventana salga en el centro de la pantalla
        loginButton.addActionListener(e -> { loginUser(); });
    }
    private void loginUser(){
        String user = txtUsuario.getText();
        String pass = new String(passwordField1.getPassword());

        System.out.println(user + " : " + pass);

        if(!validateUser(user,pass)){
            info.setText("Usuario incorrecto");
            //info.setForeground(Color.RED);
            txtUsuario.setText("");
            passwordField1.setText("");
        } else{
            this.dispose();
            Principal ventana = new Principal();
            ventana.load();
             }
    }

    private boolean validateUser(String user, String pass){
        return ("xemi".equals(user) && "1234".equals(pass));
    }

    public void mostrar(){
        this.setVisible(true);
    }
}


