package com.example.demo.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserApplication  {

    public void start(Stage stage) throws IOException {
        // Adjust the path to the FXML file under the correct package
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/user-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("User Management System");
        stage.setScene(scene);
        stage.show();
    }


}
