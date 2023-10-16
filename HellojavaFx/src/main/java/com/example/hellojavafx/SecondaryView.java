package com.example.hellojavafx;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class SecondaryView implements Initializable
{

    @javafx.fxml.FXML
    private Label info;
    @javafx.fxml.FXML
    private TextArea taTexto;
    @javafx.fxml.FXML
    private Button btnSaludar;
    @javafx.fxml.FXML
    private Button btnDespedir;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @javafx.fxml.FXML
    public void saludar(ActionEvent actionEvent) {
        info.setText("Hola "+taTexto.getText());

        var alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText("Hola " + taTexto.getText());
        alerta.showAndWait();
    }

    @javafx.fxml.FXML
    public void despedir(ActionEvent actionEvent) {
        info.setText("Adios "+taTexto.getText());
    }
}