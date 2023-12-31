    package com.example.gestionpedidos.ui;

    import com.example.gestionpedidos.persistence.ConsultasDB;
    import javafx.application.Platform;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.fxml.Initializable;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.*;
    import javafx.stage.Stage;

    import java.io.IOException;
    import java.net.URL;
    import java.util.ResourceBundle;

    public class VentanaLogin implements Initializable {
        @FXML
        private TextField tfUser;
        @FXML
        private Button btnSession;
        @FXML
        private Button bntCancel;

        private VentanaPrincipal ventanaPrincipal;
        @FXML
        private PasswordField tfPass;

        public void setVentanaPrincipal(VentanaPrincipal ventanaPrincipal) {
            this.ventanaPrincipal = ventanaPrincipal;
        }

        @FXML
        protected void onLoginButtonClick(ActionEvent event) {
            String usuarioIngresado = tfUser.getText();
            String contraseñaIngresada = tfPass.getText();

            if (ConsultasDB.verificarCredenciales(usuarioIngresado, contraseñaIngresada)) {
                // Cierra la ventana actual (de inicio de sesión)
                Stage stage = (Stage) btnSession.getScene().getWindow();
                stage.close();

                // Crea y muestra la ventana principal
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ventanaPrincipal.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage ventanaPrincipalStage = new Stage();
                    ventanaPrincipalStage.setTitle("Ventana Principal");
                    ventanaPrincipalStage.setScene(scene);
                    ventanaPrincipalStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
            // Cierra la aplicación
            Platform.exit();
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

        }
    }