package com.example.gestionpedidoscondao.ui;

import com.example.gestionpedidoscondao.Session;
import com.example.gestionpedidoscondao.model.ItemPedido;
import com.example.gestionpedidoscondao.model.Pedido;
import com.example.gestionpedidoscondao.persistence.ItemPedidoDAO;
import com.example.gestionpedidoscondao.persistence.ItemPedidoDAOImp;
import com.example.gestionpedidoscondao.persistence.PedidoDAO;
import com.example.gestionpedidoscondao.persistence.PedidoDAOImp;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VentanaItemPedido extends Application implements Initializable {

    @javafx.fxml.FXML
    private TableView<ItemPedido> tbItemsPedidos;
    @javafx.fxml.FXML
    private TableColumn cCodPedido;
    @javafx.fxml.FXML
    private TableColumn cCantidad;
    @javafx.fxml.FXML
    private Button btnVolver;
    @javafx.fxml.FXML
    private TableColumn cId_item;
    @javafx.fxml.FXML
    private TableColumn cidProducto;
    private ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAOImp();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }

    public void loadItemsPedido() {
        System.out.println("Cargando items del pedido: " + Session.getPedido());

        if(itemPedidoDAO == null) {
            System.out.println("Error: itemPedidoDAO no ha sido inicializado");
            return;
        }

        List<ItemPedido> items = itemPedidoDAO.findItemsByPedidoCodigo(Session.getPedido());

        cId_item.setCellValueFactory(new PropertyValueFactory<ItemPedido, Integer>("id"));
        cCodPedido.setCellValueFactory(new PropertyValueFactory<ItemPedido, Integer>("pedidoId"));
        cidProducto.setCellValueFactory(new PropertyValueFactory<ItemPedido, Integer>("productoId"));
        cCantidad.setCellValueFactory(new PropertyValueFactory<ItemPedido, Integer>("cantidad"));

        tbItemsPedidos.setItems(FXCollections.observableArrayList(items));
    }


    @javafx.fxml.FXML
    public void volver(ActionEvent actionEvent) throws IOException {
        // Cierra la ventana actual
        Stage stageActual = (Stage) btnVolver.getScene().getWindow();
        stageActual.close();

        // Abre VentanaPrincipal
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ventanaPrincipal.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Ventana Principal");
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadItemsPedido();
    }
}
