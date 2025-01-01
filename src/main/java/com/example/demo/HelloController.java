package com.example.demo;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import com.example.demo.util.DatabaseConnection;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class HelloController {
    Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    private Label welcomeText;


    @FXML
    public void initialize() {

    }
    public void switchToLoginAfterDelay() {
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {
            try {

                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
                Scene loginScene = new Scene(fxmlLoader.load(), 320, 240);

                stage.setScene(loginScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        pause.play();
    }
}