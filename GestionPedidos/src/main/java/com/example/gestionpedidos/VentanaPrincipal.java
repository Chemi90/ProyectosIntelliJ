package com.example.gestionpedidos;

import javafx.event.ActionEvent;

public class VentanaPrincipal {

    @javafx.fxml.FXML
    public void volver(ActionEvent actionEvent) {
        HelloApplication.loadFXML("ventanaPrincipal.fxml");
    }
}
