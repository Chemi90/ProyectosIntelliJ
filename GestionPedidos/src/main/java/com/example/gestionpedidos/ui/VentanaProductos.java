package com.example.gestionpedidos.ui;

import com.example.gestionpedidos.model.ItemPedido;
import com.example.gestionpedidos.persistence.ConsultasDB;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VentanaProductos extends Application implements Initializable {

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

    @FXML
    public void mostrarElementosDelPedido(String codigoPedido) {
        ConsultasDB db = new ConsultasDB();

        // Realiza una consulta a la base de datos para obtener los elementos del pedido
        List<ItemPedido> elementosPedido = db.getElementosPedido(codigoPedido);

        // Llena la tabla tbItemsPedidos con los elementos del pedido
        cCodPedido.setCellValueFactory(new PropertyValueFactory<>("codigoPedido"));
        cCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        cidProducto.setCellValueFactory(new PropertyValueFactory<>("idProducto"));

        tbItemsPedidos.setItems(FXCollections.observableArrayList(elementosPedido));
    }


    @javafx.fxml.FXML
    public void volver(ActionEvent actionEvent) {
        // Cerrar la ventana actual
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();

        // Mostrar la instancia almacenada de VentanaPrincipal
        VentanaPrincipal ventanaPrincipal = VentanaPrincipal.getInstancia();
        if (ventanaPrincipal != null) {
            Stage ventanaPrincipalStage = new Stage();
            Scene scene = new Scene(ventanaPrincipal.getVentana());
            ventanaPrincipalStage.setScene(scene);
            ventanaPrincipalStage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
