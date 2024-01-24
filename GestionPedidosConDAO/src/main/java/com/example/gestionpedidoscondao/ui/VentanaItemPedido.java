package com.example.gestionpedidoscondao.ui;

import com.example.gestionpedidoscondao.App;
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

/**
 * Controlador de la UI para la ventana que muestra los items de un pedido.
 * Esta clase gestiona las interacciones del usuario y la presentación de
 * los datos de los items del pedido en la tabla correspondiente.
 *
 * @author José Miguel Ruiz Guevara
 * @version 1.0
 * @since 1.0
 */
public class VentanaItemPedido extends Application implements Initializable {

    @javafx.fxml.FXML
    private TableView<ItemPedido> tbItemsPedidos;
    @javafx.fxml.FXML
    private TableColumn<ItemPedido, String> cnomProducto;
    @javafx.fxml.FXML
    private TableColumn<ItemPedido, Double> cprecioProducto;
    @javafx.fxml.FXML
    private TableColumn<ItemPedido, Integer> cCantidad;
    @javafx.fxml.FXML
    private Button btnVolver;

    private ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAOImp();

    /**
     * Punto de entrada principal para la aplicación JavaFX.
     *
     * @param args argumentos pasados a la aplicación.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Este método es llamado por el runtime de JavaFX al iniciar la aplicación.
     * Se usa para inicializar el estado inicial de la aplicación.
     *
     * @param primaryStage el escenario principal para esta aplicación.
     */
    @Override
    public void start(Stage primaryStage) {
        // Implementación del método start
    }

    /**
     * Carga los items del pedido en la tabla de la interfaz de usuario.
     * Hace uso de la sesión actual para obtener los datos relevantes.
     */
    public void loadItemsPedido() {
        System.out.println("Cargando items del pedido: " + Session.getPedido());

        if(itemPedidoDAO == null) {
            System.out.println("Error: itemPedidoDAO no ha sido inicializado");
            return;
        }

        List<ItemPedido> items = itemPedidoDAO.findItemsByPedidoCodigo(Session.getPedido());

        cprecioProducto.setCellValueFactory(new PropertyValueFactory<>("precio"));
        cnomProducto.setCellValueFactory(new PropertyValueFactory<>("productoNombre"));
        cCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        tbItemsPedidos.setItems(FXCollections.observableArrayList(items));
    }

    /**
     * Maneja el evento de clic en el botón de volver.
     * Cambia la vista a la ventana principal de la aplicación.
     *
     * @param actionEvent El evento que desencadenó el método.
     * @throws IOException Si ocurre un error durante el cambio de escena.
     */
    @javafx.fxml.FXML
    public void volver(ActionEvent actionEvent) throws IOException {
        App.changeScene("ventanaPrincipal.fxml", "Gestor de Pedidos");
    }

    /**
     * Inicializa el controlador después de que su elemento raíz ha sido
     * completamente procesado.
     *
     * @param url El location utilizado para resolver rutas relativas para el objeto raíz, o null si la location no es conocida.
     * @param resourceBundle El recurso usado para localizar el objeto raíz, o null si el recurso no fue encontrado.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadItemsPedido();
    }
}
