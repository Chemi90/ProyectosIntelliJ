package com.example.gestionpedidos.ui;

import com.example.gestionpedidos.model.ItemPedido;
import com.example.gestionpedidos.persistence.ConsultasDB;
import com.example.gestionpedidos.model.Carrito;
import com.example.gestionpedidos.model.Pedidos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.util.List;
import java.util.ResourceBundle;

public class VentanaPrincipal implements Initializable {
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
    @FXML
    private TableColumn cNombre;
    @FXML
    private TableColumn cCantidadCarrito;
    @FXML
    private TableColumn cPrecioTotal;
    @FXML
    private MenuItem mbClose;
    private static VentanaPrincipal instancia;
    private String pedidoSeleccionadoCodigo;


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

        instancia = this;
    }

    public static VentanaPrincipal getInstancia() {
        return instancia;
    }

    public Parent getVentana() {
        return (Parent) lbLogo.getScene().getRoot();
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
    protected void onViewItemsClick(ActionEvent event) throws IOException {
        if (pedidoSeleccionadoCodigo != null) {
            VentanaProductos ventanaProductos = new VentanaProductos();
            List<ItemPedido> elementosPedido = ConsultasDB.getElementosPedido(pedidoSeleccionadoCodigo);

            // Mostrar la ventana de productos y pasar los elementos del pedido
            ventanaProductos.mostrarElementosDelPedido(pedidoSeleccionadoCodigo);

            // Crear un nuevo escenario y mostrar la ventana de productos
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ventanaProductos.fxml"));
            Stage ventanaProductosStage = new Stage();
            Scene scene = new Scene(loader.load());
            ventanaProductosStage.setScene(scene);
            ventanaProductosStage.setTitle("Detalles del Pedido");
            ventanaProductosStage.show();
        } else {
            // Muestra un mensaje de error en caso de no haber seleccionado un pedido
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error carga");
            alert.setHeaderText("Problemas carga tabla");
            alert.setContentText("Por favor, verifica todos los datos.");
            alert.showAndWait();
        }
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

        // Llena las columnas de la tabla tbCarrito (si no se han llenado previamente)
        if (cNombre.getCellData(0) == null) {
            cNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            cCantidadCarrito.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            cPrecioTotal.setCellValueFactory(new PropertyValueFactory<>("precioTotal"));
            tbCarrito.getColumns().setAll(cNombre, cCantidadCarrito, cPrecioTotal);
        }
    }

    @FXML
    protected void onComprarClick(ActionEvent event) {
        // Obtener los elementos del carrito actual
        ObservableList<Carrito> itemsCarrito = tbCarrito.getItems();

        if (itemsCarrito.isEmpty()) {
            // No se puede realizar la compra sin elementos en el carrito
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en la compra");
            alert.setHeaderText("Carrito vacío");
            alert.setContentText("Agrega productos al carrito antes de realizar la compra.");
            alert.showAndWait();
            return;
        }

        ConsultasDB db = new ConsultasDB();

        // Calcular el total de la compra
        double totalCompra = 0;
        for (Carrito item : itemsCarrito) {
            totalCompra += item.getPrecioTotal();
        }

        // Crear un nuevo pedido
        String codigoPedido = generateUniqueCode(); // Debes implementar una función para generar un código único
        // Obtener la fecha actual
        long tiempoActual = System.currentTimeMillis();
        java.sql.Date fechaPedido = new java.sql.Date(tiempoActual);
        Integer usuario = ConsultasDB.getUsuarioIdLogeado(); // Obtener el usuario de la sesión

        // Insertar el pedido en la base de datos
        if (db.insertarPedido(codigoPedido, fechaPedido, usuario, totalCompra, itemsCarrito)) {
            // Éxito: limpiar el carrito
            itemsCarrito.clear();
            // Actualizar la tabla del carrito
            tbCarrito.getItems().clear();

            // Refrescar la tabla de pedidos
            List<Pedidos> pedidosDelUsuario = db.getPedidosDelUsuario(ConsultasDB.getUsuarioLogeado());
            tbPedidos.setItems(FXCollections.observableArrayList(pedidosDelUsuario));

            // Notificar al usuario sobre la compra exitosa
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Compra exitosa");
            alert.setHeaderText("Compra realizada con éxito");
            alert.setContentText("Tu pedido ha sido registrado correctamente.");
            alert.showAndWait();
        } else {
            // Error al insertar el pedido en la base de datos
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en la compra");
            alert.setHeaderText("Error al registrar el pedido");
            alert.setContentText("Hubo un problema al registrar tu pedido. Inténtalo de nuevo.");
            alert.showAndWait();
        }
    }

    public String generateUniqueCode() {
        long timestamp = System.currentTimeMillis();
        int randomValue = (int) (Math.random() * 1000);
        String uniqueCode = "PEDIDO_" + timestamp + "_" + randomValue;
        return uniqueCode;
    }

    @FXML
    protected void onCancelarClick(ActionEvent event) {
        // Limpiar el carrito
        tbCarrito.getItems().clear();
    }

    public void cerrarVentana() {
        Stage stage = (Stage) lbLogo.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onLogoutClick(ActionEvent event) {
        // Limpia los datos y cierra la ventana principal
        cerrarVentana();

        // Volver a la pantalla de inicio de sesión
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
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


    @FXML
    protected void onCloseClick(ActionEvent event) {
        // Cerrar la aplicación
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
}

