package com.example.demo.controller;

import com.example.demo.HelloApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Login extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        try {
            // Load the login-view.fxml file
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));

            // Set the controller factory to pass the Stage to the controller
            fxmlLoader.setControllerFactory(controllerClass -> new LoginController());

            // Load the scene
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);

            // Set the stage and show the login page
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
