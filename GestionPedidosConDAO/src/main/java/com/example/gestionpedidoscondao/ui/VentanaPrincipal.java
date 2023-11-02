package com.example.gestionpedidoscondao.ui;

import com.example.gestionpedidoscondao.Session;
import com.example.gestionpedidoscondao.model.Pedido;
import com.example.gestionpedidoscondao.model.Producto;
import com.example.gestionpedidoscondao.persistence.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class VentanaPrincipal extends Application implements Initializable {

    @javafx.fxml.FXML
    private Label lbLogo;
    @javafx.fxml.FXML
    private TableView tbPedidos;
    @javafx.fxml.FXML
    private TableColumn cCodigo;
    @javafx.fxml.FXML
    private TableColumn cFecha;
    @javafx.fxml.FXML
    private TableColumn cTotal;
    @javafx.fxml.FXML
    private TableView tbCarrito;
    @javafx.fxml.FXML
    private TableColumn cNombre;
    @javafx.fxml.FXML
    private TableColumn cCantidadCarrito;
    @javafx.fxml.FXML
    private TableColumn cPrecioTotal;
    @javafx.fxml.FXML
    private Button btnAceptar;
    @javafx.fxml.FXML
    private Button btnCancelar;
    @javafx.fxml.FXML
    private Label lbNombreUsuario;
    @javafx.fxml.FXML
    private ComboBox cbItem;
    @javafx.fxml.FXML
    private Label lbPrecio;
    @javafx.fxml.FXML
    private ComboBox cbCantidad;
    @javafx.fxml.FXML
    private Button bntAñadir;
    @javafx.fxml.FXML
    private MenuItem mbLogout;
    @javafx.fxml.FXML
    private MenuItem mbClose;
    private ProductoDAO productoDAO = new ProductoDAOImp();
    private PedidoDAO pedidoDAO = new PedidoDAOImp();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    }

    @javafx.fxml.FXML
    public void onViewItemsClick(Event event) throws IOException {
        Pedido pedidoSeleccionado = (Pedido) tbPedidos.getSelectionModel().getSelectedItem();
        if (pedidoSeleccionado != null) {
            // Cierra la ventana actual
            Stage stageActual = (Stage) lbNombreUsuario.getScene().getWindow();
            stageActual.close();

            String CodPedido = pedidoSeleccionado.getCódigo();
            Session.setPedido(CodPedido);
            mostrarVentanaItemPedido(pedidoSeleccionado);
        } else {
            // Mostrar mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Pedido");
            alert.setHeaderText(null);
            alert.setContentText("No has seleccionado ningún pedido.");
            alert.showAndWait();
        }
    }


    @Deprecated
    private void mostrarVentanaItemPedido(Pedido pedido) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ventanaItemPedido.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Detalles del pedido");
        stage.show();
    }



    @javafx.fxml.FXML
    public void onComprarClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void onCancelarClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void onAñadirClick(ActionEvent actionEvent) {
    }
    public void cerrarVentana() {
        Stage stage = (Stage) lbLogo.getScene().getWindow();
        stage.close();
    }
    @javafx.fxml.FXML
    public void onLogoutClick(ActionEvent actionEvent) {
        // Limpia los datos y cierra la ventana principal
        cerrarVentana();
        // Volver a la pantalla de inicio de sesión
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ventanaLogin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage loginStage = new Stage();
            loginStage.setTitle("Inicio de Sesión");
            loginStage.setScene(scene);
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void onCloseClick(ActionEvent actionEvent) {
        // Cerrar la aplicación
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbNombreUsuario.setText(Session.getUsuarioLogeado());
        loadNombresProductosIntoComboBox();
        loadPedidosUsuario();
    }

    private void loadPedidosUsuario() {
        List<Pedido> pedidos = pedidoDAO.findByUsuarioId(Session.getUsuarioId());

        cCodigo.setCellValueFactory(new PropertyValueFactory<Pedido, String>("código"));
        cFecha.setCellValueFactory(new PropertyValueFactory<Pedido, Date>("fecha"));
        cTotal.setCellValueFactory(new PropertyValueFactory<Pedido, Double>("total"));
        tbPedidos.setItems(FXCollections.observableArrayList(pedidos));
    }

    private void loadPrecioProductosIntoLabel(Producto producto){
        if (producto != null) {
            lbPrecio.setText(String.format("$%.2f", producto.getPrecio()));
        } else {
            lbPrecio.setText("$0.00");
        }
    }

    private void loadNombresProductosIntoComboBox() {
        List<Producto> productos = productoDAO.findAll();
        List<String> nombreProductos = new ArrayList<>();
        for (Producto producto : productos) {
            nombreProductos.add(producto.getNombre());
        }
        cbItem.getItems().addAll(nombreProductos);
        listenerProductoSeleccionado(productos);
    }

    private void listenerProductoSeleccionado(List<Producto> productos) {
        cbItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Producto selectedProducto = productos.stream()
                    .filter(producto -> producto.getNombre().equals(newValue))
                    .findFirst()
                    .orElse(null);
            if (selectedProducto != null) {
                updateCantidadComboBox(selectedProducto);
                loadPrecioProductosIntoLabel(selectedProducto);
            }
        });
    }

    private void updateCantidadComboBox(Producto producto) {
        cbCantidad.getItems().clear();
        if (producto != null) {
            for (int i = 1; i <= producto.getCantidadDisponible(); i++) {
                cbCantidad.getItems().add(i);
            }
        }
    }
}
