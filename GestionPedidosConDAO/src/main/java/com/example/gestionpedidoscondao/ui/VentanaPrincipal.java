package com.example.gestionpedidoscondao.ui;

import com.example.gestionpedidoscondao.App;
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

/**
 * Clase controladora de la interfaz de usuario principal de la aplicación.
 * Gestiona la interacción con el usuario y realiza la navegación entre diferentes vistas.
 *
 * @author José Miguel Ruiz Guevara
 * @version 1.0
 */
public class VentanaPrincipal extends Application implements Initializable {

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


    /**
     * Punto de entrada principal para la aplicación de JavaFX.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Inicializa la ventana principal de la aplicación con el escenario proporcionado.
     *
     * @param primaryStage El escenario principal para esta aplicación, sobre el cual
     *                     la aplicación se desarrolla.
     */
    @Override
    public void start(Stage primaryStage) {
    }

    /**
     * Se llama a este método para notificar que se ha hecho clic en el botón "Ver Items".
     * Realiza la acción de navegar a la vista de items de un pedido seleccionado.
     *
     * @param event El evento de clic que disparó el método.
     * @throws IOException Si ocurre un error durante la carga de la vista.
     */
    @javafx.fxml.FXML
    public void onViewItemsClick(Event event) throws IOException {
        Pedido pedidoSeleccionado = (Pedido) tbPedidos.getSelectionModel().getSelectedItem();
        if (pedidoSeleccionado != null) {

            String CodPedido = pedidoSeleccionado.getCódigo();
            Session.setPedido(CodPedido);
            App.changeScene("ventanaItemPedido.fxml", "Items del Pedido " + CodPedido);
        } else {
            // Mostrar mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Pedido");
            alert.setHeaderText(null);
            alert.setContentText("No has seleccionado ningún pedido.");
            alert.showAndWait();
        }
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

    @javafx.fxml.FXML
    public void onLogoutClick(ActionEvent actionEvent) {
        try {
            App.changeScene("ventanaLogin.fxml", "Login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Manejador del evento de clic para el elemento de menú 'Cerrar'.
     * Cierra la ventana actual y, como resultado, la aplicación si esta es la única ventana abierta.
     *
     * @param actionEvent El evento de acción que desencadenó este método.
     */
    @javafx.fxml.FXML
    public void onCloseClick(ActionEvent actionEvent) {
        // Cerrar la aplicación
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    /**
     * Este método se invoca automáticamente para inicializar el controlador después de que su
     * elemento raíz ha sido completamente procesado.
     *
     * @param url            La ubicación utilizada para resolver rutas relativas para el objeto raíz,
     *                       o {@code null} si la ubicación no es conocida.
     * @param resourceBundle El recurso que se utilizó para localizar el objeto raíz, o {@code null}
     *                       si el objeto raíz no fue localizado.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbNombreUsuario.setText(Session.getUsuarioLogeado());
        loadNombresProductosIntoComboBox();
        loadPedidosUsuario();
    }

    /**
     * Carga los pedidos del usuario y actualiza la interfaz de usuario en consecuencia.
     */
    private void loadPedidosUsuario() {
        List<Pedido> pedidos = pedidoDAO.findByUsuarioId(Session.getUsuarioId());

        cCodigo.setCellValueFactory(new PropertyValueFactory<Pedido, String>("código"));
        cFecha.setCellValueFactory(new PropertyValueFactory<Pedido, Date>("fecha"));
        cTotal.setCellValueFactory(new PropertyValueFactory<Pedido, Double>("total"));
        tbPedidos.setItems(FXCollections.observableArrayList(pedidos));
    }

    /**
     * Actualiza la etiqueta de precio basándose en el producto seleccionado.
     * Si el producto es {@code null}, se establece el texto de la etiqueta a "$0.00".
     *
     * @param producto El producto cuyo precio se debe mostrar.
     */
    private void loadPrecioProductosIntoLabel(Producto producto){
        if (producto != null) {
            lbPrecio.setText(String.format("$%.2f", producto.getPrecio()));
        } else {
            lbPrecio.setText("$0.00");
        }
    }

    /**
     * Carga los nombres de todos los productos en el ComboBox para la selección del usuario.
     * Además, establece un oyente para cuando se selecciona un nuevo producto,
     * para actualizar la interfaz de usuario correspondientemente.
     */
    private void loadNombresProductosIntoComboBox() {
        List<Producto> productos = productoDAO.findAll();
        List<String> nombreProductos = new ArrayList<>();
        for (Producto producto : productos) {
            nombreProductos.add(producto.getNombre());
        }
        cbItem.getItems().addAll(nombreProductos);
        listenerProductoSeleccionado(productos);
    }

    /**
     * Añade un oyente al ComboBox de productos que actualiza la cantidad disponible y
     * el precio en la interfaz de usuario cuando se selecciona un producto.
     *
     * @param productos Lista de productos para los que se establecerá el oyente.
     */
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

    /**
     * Actualiza el ComboBox de cantidad basándose en la cantidad disponible del producto seleccionado.
     * Limpia el ComboBox actual y añade la cantidad de elementos según la disponibilidad del producto.
     *
     * @param producto El producto seleccionado cuya cantidad disponible determinará las opciones de cantidad.
     */
    private void updateCantidadComboBox(Producto producto) {
        cbCantidad.getItems().clear();
        if (producto != null) {
            for (int i = 1; i <= producto.getCantidadDisponible(); i++) {
                cbCantidad.getItems().add(i);
            }
        }
    }
}
