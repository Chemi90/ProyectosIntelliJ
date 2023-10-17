module com.example.diseniorecetariococina {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens com.example.diseniorecetariococina to javafx.fxml;
    exports com.example.diseniorecetariococina;
}