module com.example.gestionpedidos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;


    opens com.example.gestionpedidos to javafx.fxml;
    exports com.example.gestionpedidos;
    exports com.example.gestionpedidos.model;
    opens com.example.gestionpedidos.model to javafx.fxml;
    exports com.example.gestionpedidos.persistence;
    opens com.example.gestionpedidos.persistence to javafx.fxml;
    exports com.example.gestionpedidos.ui;
    opens com.example.gestionpedidos.ui to javafx.fxml;
}