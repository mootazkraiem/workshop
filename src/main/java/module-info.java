module com.example.demo.controller {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;  // If you are using a database connection


    exports com.example.demo.controller;
    exports com.example.demo;  // Export the com.example.demo.controller package so JavaFX can access it
    opens com.example.demo to javafx.fxml;
    opens com.example.demo.controller to javafx.fxml;// Allow reflection for FXML controllers
}
