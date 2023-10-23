package com.example.diseniorecetariococina;

import com.example.diseniorecetariococina.models.Receta;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;

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
    private ListView<String> listTipo;
    @FXML
    private Button btnAñadir;
    @FXML
    private TableView<Receta> tabla;
    @FXML
    private TableColumn<Receta, String> cNombre;
    @FXML
    private TableColumn<Receta, String> cDuracion;
    @FXML
    private TableColumn<Receta, String> cDificultad;
    @FXML
    private TableColumn<Receta, String> cTipo;
    @FXML
    private Label lblDuracion;
    @FXML
    private TextField txtNombre;
    @FXML
    private MenuItem menuSalir;
    @FXML
    private MenuItem menuAcercaDe;
    @FXML
    private ComboBox<Receta> comboRecetas;

    @FXML
    public void insertarReceta(ActionEvent actionEvent) {

        if(txtNombre.getText().length()>0){
            Receta receta = new Receta();
            receta.setNombre(txtNombre.getText());
            receta.setTipo(listTipo.getSelectionModel().getSelectedItem());
            receta.setDuracion((int) sliderDuracion.getValue());
            receta.setDificultad((String) comboDificultad.getSelectionModel().getSelectedItem());
            tabla.getItems().add(receta);
            info.setText(receta.toString());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /*
        comboDificultad.getItems().add("Fácil");
        comboDificultad.getItems().add("Medio");
        comboDificultad.getItems().add("Difícil");

        comboDificultad.getItems().addAll("Básica", "Hard", "Pro");
         */

        ObservableList<String> datos = FXCollections.observableArrayList();
        datos.addAll("Fácil", "Medio", "Dificil", "Moderada");
        comboDificultad.setItems(datos);
        comboDificultad.getSelectionModel().selectFirst();

        sliderDuracion.setValue(60);
        lblDuracion.setText(Math.round(sliderDuracion.getValue())+" min");

        listTipo.getItems().addAll("Desayuno", "Segundo Desayuno", "Almuerzo", "SobreAlmuerzo", "Merienda", "Cena", "ReCena", "PostCena");
        listTipo.getSelectionModel().select(0);

        sliderDuracion.valueProperty().addListener((observableValue, number, t1) -> lblDuracion.setText(Math.round(t1.intValue())+" min"));

        tabla.getSelectionModel().selectedItemProperty().addListener(
                (observable, vOld, vNew) -> {
                    info.setText(vNew.toString());
                    txtNombre.setText(vNew.getNombre());
                    sliderDuracion.setValue(vNew.getDuracion());
                    listTipo.getSelectionModel().select(vNew.getTipo());
                    comboDificultad.getSelectionModel().select(vNew.getDificultad());
                }
        );

        cNombre.setCellValueFactory((fila) -> new SimpleStringProperty(fila.getValue().getNombre()));
        cDificultad.setCellValueFactory((fila) -> new SimpleStringProperty(fila.getValue().getDificultad()));
        cDuracion.setCellValueFactory((fila) -> new SimpleStringProperty(fila.getValue().getDuracion().toString()));
        cTipo.setCellValueFactory((fila) -> new SimpleStringProperty(fila.getValue().getTipo()));

        tabla.getItems().add(new Receta("Tacos de carne asada", "Almuerzo", 45, "Fácil"));
        tabla.getItems().add(new Receta("Huevos revueltos con tocino", "Desayuno", 15, "Moderada"));
        tabla.getItems().add(new Receta("Sándwich de jamón y queso", "Merienda", 10, "Fácil"));
        tabla.getItems().add(new Receta("Pollo a la parrilla con verduras", "Almuerzo", 60, "Moderada"));
        tabla.getItems().add(new Receta("Avena con frutas", "Desayuno", 20, "Fácil"));
        tabla.getItems().add(new Receta("Ensalada de atún", "Almuerzo", 30, "Moderada"));

        comboRecetas.setConverter(new StringConverter<Receta>() {
            @Override
            public String toString(Receta receta) {
                if(receta!=null)return receta.getNombre();
                else return "";
            }

            @Override
            public Receta fromString(String s) {
                return null;
            }
        });
        comboRecetas.getItems().addAll(tabla.getItems());
    }

    @FXML
    public void actualizarDuracion(Event event) {
       // lblDuracion.setText(Math.round(sliderDuracion.getValue())+" min");
    }

    @FXML
    public void salir(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    public void mostrarAcercaDe(ActionEvent actionEvent) {
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("El creador");
        alert.setContentText("Creado por Jose Miguel");
        alert.showAndWait();
    }

    @FXML
    public void mostrarRecetas(ActionEvent actionEvent) {
    }
}