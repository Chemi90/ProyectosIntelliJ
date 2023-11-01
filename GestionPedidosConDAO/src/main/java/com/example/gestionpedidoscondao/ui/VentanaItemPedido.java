package com.example.gestionpedidoscondao.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class VentanaItemPedido extends Application {

    @javafx.fxml.FXML
    private TableView tbItemsPedidos;
    @javafx.fxml.FXML
    private TableColumn cCodPedido;
    @javafx.fxml.FXML
    private TableColumn cCantidad;
    @javafx.fxml.FXML
    private TableColumn cidProducto;
    @javafx.fxml.FXML
    private Button btnVolver;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }

    @javafx.fxml.FXML
    public void volver(ActionEvent actionEvent) {
    }
}
