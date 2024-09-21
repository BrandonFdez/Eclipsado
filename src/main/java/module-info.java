module com.example.eclipsado {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.eclipsado to javafx.fxml;
    exports com.example.eclipsado;
    exports com.example.eclipsado.controller;
    opens com.example.eclipsado.controller to javafx.fxml;
}