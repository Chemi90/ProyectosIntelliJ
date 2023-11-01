package com.example.gestionpedidoscondao.ui;

import com.example.gestionpedidoscondao.Session;
import com.example.gestionpedidoscondao.model.Usuario;
import com.example.gestionpedidoscondao.persistence.UsuarioDAO;
import com.example.gestionpedidoscondao.persistence.UsuarioDAOImp;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class VentanaLogin {

    @FXML
    private TextField tfUser;
    @FXML
    private PasswordField tfPass;
    @FXML
    private Button btnSession;
    @FXML
    private Button bntCancel;
    private final UsuarioDAO usuarioDAO = new UsuarioDAOImp();

    @FXML
    private void onLoginButtonClick() throws Exception {
        String nombre = tfUser.getText();
        String password = tfPass.getText();

        List<Usuario> usuarios = usuarioDAO.findAll();
        Usuario user = null;
        for(Usuario u : usuarios) {
            if(u.getNombre().equals(nombre) && u.getContraseña().equals(password)) {
                user = u;
                break;
            }
        }

        if (user != null) {
            // Guarda el ID y el nombre del usuario en Session
            Session.setUsuarioLogeado(user.getNombre());
            Session.setUsuarioId(user.getId());

            // Cierra la ventana actual
            Stage stageActual = (Stage) tfUser.getScene().getWindow();
            stageActual.close();

            // Abre VentanaPrincipal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ventanaPrincipal.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Ventana Principal");
            stage.show();

        } else {
            // Mostrar mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Login");
            alert.setHeaderText(null);
            alert.setContentText("Usuario o contraseña incorrectos.");
            alert.showAndWait();
        }
    }

    @FXML
    public void onCancelButtonClick(ActionEvent actionEvent) {
        // Acción para el botón "Cancelar" (puedes agregar lo que sea necesario)
        tfUser.clear(); // Limpia los campos de usuario y contraseña
        tfPass.clear();
        // Cierra la aplicación
        Platform.exit();
    }
}