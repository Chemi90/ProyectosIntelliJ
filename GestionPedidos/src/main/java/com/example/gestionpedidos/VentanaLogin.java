package com.example.gestionpedidos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class VentanaLogin implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField tfUser;
    @FXML
    private TextField tfPass;
    @FXML
    private Button btnSession;
    @FXML
    private Button bntCancel;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onLoginButtonClick(ActionEvent event) {

        String usuarioIngresado = tfUser.getText();
        String contraseñaIngresada = tfPass.getText();

        if (ConsultasDB.verificarCredenciales(usuarioIngresado, contraseñaIngresada)) {
            // Inicio de sesión exitoso, abre la ventana principal
            HelloApplication.loadFXML("ventanaPrincipal.fxml");
        } else {
            // Muestra un mensaje de error en una ventana emergente
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de inicio de sesión");
            alert.setHeaderText("Credenciales incorrectas");
            alert.setContentText("Por favor, verifica tu nombre de usuario y contraseña.");
            alert.showAndWait();
        }
    }
    @FXML
    protected void onCancelButtonClick(ActionEvent event) {
        // Acción para el botón "Cancelar" (puedes agregar lo que sea necesario)
        tfUser.clear(); // Limpia los campos de usuario y contraseña
        tfPass.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}