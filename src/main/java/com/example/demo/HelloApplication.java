package com.example.demo;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        // Pass the stage to the controller for scene switching
        HelloController controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setTitle("WorkSpace Reservation");
        stage.setScene(scene);
        stage.show();

        // Automatically move to the login screen after 3 seconds
        controller.switchToLoginAfterDelay();
    }


    public static void main(String[] args) {
        launch();
    }
}