package com.example.gestionpedidos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {


    private static Stage myStage;
    /*
    public static void loadFXML(String fxml, double width, double height) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
            Parent root = fxmlLoader.load(); // Utiliza Parent en lugar de Pane
            Scene scene = new Scene(root, width, height);
            myStage.setScene(scene);
        } catch (IOException e) {
            System.out.println("Error al cargar el FXML");
            throw new RuntimeException(e);
        }
    }

     */

    @Override
    public void start(Stage stage) throws IOException {
        myStage = stage;
        FXMLLoader fxmlLogin = new FXMLLoader(App.class.getResource("ui/login.fxml"));
        Scene scene = new Scene(fxmlLogin.load(), 300, 300);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}