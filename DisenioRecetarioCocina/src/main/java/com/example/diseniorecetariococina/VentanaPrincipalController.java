package com.example.diseniorecetariococina;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class VentanaPrincipalController implements Initializable {

    @FXML
    private Label info;
    @FXML
    private Label txtNuevaReceta;
    @FXML
    private Slider sliderDuracion;
    @FXML
    private ComboBox comboDificultad;
    @FXML
    private ListView listTipo;
    @FXML
    private Button btnAñadir;
    @FXML
    private TableView tabla;
    @FXML
    private TableColumn cNombre;
    @FXML
    private TableColumn cDuracion;
    @FXML
    private TableColumn cDificultad;
    @FXML
    private TableColumn cTipo;

    @FXML
    public void insertarReceta(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}