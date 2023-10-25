module com.example.recetariodecocina {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires javafx.media;

    opens com.example.recetariodecocina.img;
    opens com.example.recetariodecocina.audio;

    opens com.example.recetariodecocina to javafx.fxml;
    exports com.example.recetariodecocina;
}