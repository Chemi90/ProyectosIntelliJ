package com.example.gestionpedidos;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VentanaPrincipal implements Initializable {
    @javafx.fxml.FXML
    private Label lbLogo;
    @javafx.fxml.FXML
    private TableView tbPedidos;
    @javafx.fxml.FXML
    private TableColumn cId;
    @javafx.fxml.FXML
    private TableColumn cCodigo;
    @javafx.fxml.FXML
    private TableColumn cFecha;
    @javafx.fxml.FXML
    private TableColumn cTotal;
    @javafx.fxml.FXML
    private TableView tbCarrito;
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
    private Button bntAñadir;
    @javafx.fxml.FXML
    private MenuItem mbLogout;
    @javafx.fxml.FXML
    private ComboBox cbCantidad;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ConsultasDB db = new ConsultasDB();
        List<String> productNames = db.getProductNames();

        // Cargar los nombres de los productos en el ComboBox cbItem
        cbItem.getItems().addAll(productNames);

        precio(db);
        cantidad(db);

        lbNombreUsuario.setText(ConsultasDB.getUsuarioLogeado());

        List<Pedidos> pedidosDelUsuario = db.getPedidosDelUsuario(ConsultasDB.getUsuarioLogeado());

        // Llena la tabla tbPedidos con los pedidos del usuario actual
        cCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoPedido"));
        cFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        cTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        tbPedidos.setItems(FXCollections.observableArrayList(pedidosDelUsuario));
    }

    private void cantidad(ConsultasDB db) {
        cbItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Cuando se selecciona un nuevo producto, obtener su precio y mostrarlo en lbPrecio
                Double price = db.getProductPrice((String) newValue);
                lbPrecio.setText("Precio: " + price);

                // Obtener y mostrar la cantidad máxima disponible para el producto
                int maxQuantity = db.getProductQuantity((String) newValue);
                cbCantidad.getItems().clear();
                for (int i = 1; i <= maxQuantity; i++) {
                    cbCantidad.getItems().add(i);
                }
            }
        });
    }

    private void precio(ConsultasDB db) {
        cbItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Cuando se selecciona un nuevo producto, obtener su precio y mostrarlo en lbPrecio
                Double price = db.getProductPrice((String) newValue);
                lbPrecio.setText("Precio: " + price);
            }
        });
    }

    @FXML
    protected void onAñadirClick(ActionEvent event) {
        String producto = cbItem.getValue().toString(); // Obtener el producto seleccionado
        int cantidad = (int) cbCantidad.getValue(); // Obtener la cantidad seleccionada
        Double precioUnitario = Double.parseDouble(lbPrecio.getText().replace("Precio: ", ""));
        Double total = cantidad * precioUnitario; // Calcular el total

        // Crear un nuevo elemento para el carrito
        Carrito carritoItem = new Carrito(producto, cantidad, total);

        // Agregar el elemento al TableView tbCarrito
        tbCarrito.getItems().add(carritoItem);

        // Llena las columnas de la tabla tbCarrito
        TableColumn<Carrito, String> cNombre = new TableColumn<>("Nombre");
        TableColumn<Carrito, Integer> cCantidadCarrito = new TableColumn<>("Cantidad");
        TableColumn<Carrito, Double> cPrecioTotal = new TableColumn<>("Precio Total");

        cNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        cCantidadCarrito.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        cPrecioTotal.setCellValueFactory(new PropertyValueFactory<>("precioTotal"));

        tbCarrito.getColumns().addAll(cNombre, cCantidadCarrito, cPrecioTotal);
    }
}

