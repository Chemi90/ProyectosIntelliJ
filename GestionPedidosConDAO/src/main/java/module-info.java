module com.example.gestionpedidoscondao {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;


    opens com.example.gestionpedidoscondao to javafx.fxml;
    exports com.example.gestionpedidoscondao;
    exports com.example.gestionpedidoscondao.ui;
    opens com.example.gestionpedidoscondao.ui to javafx.fxml;
    opens com.example.gestionpedidoscondao.model to javafx.base;
}