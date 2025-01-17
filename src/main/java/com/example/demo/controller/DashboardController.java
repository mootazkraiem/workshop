package com.example.demo.controller;

import com.example.demo.HelloApplication;
import com.example.demo.entity.CurrentUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class DashboardController {

    @FXML
    private StackPane contentPane;

    @FXML
    private Button btnReservation;

    @FXML
    private Button btnHistory;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnLogout;

    @FXML
    private void navigateToReservation() {
        loadView("reservation-view.fxml");
    }

    @FXML
    private void navigateToHistory() {
        loadView("history-view.fxml");
    }

    @FXML
    private void navigateToProfile() {
        loadView("profile-view.fxml");
    }

    @FXML
    private void logout() {
        CurrentUser currentUser= CurrentUser.getInstance();
        currentUser.clear();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
            Parent loginRoot = fxmlLoader.load();

            Stage stage = (Stage) btnLogout.getScene().getWindow();
            stage.setScene(new Scene(loginRoot, 320, 240));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    private void loadView(String fxmlFile) {
        try {
            Parent view = FXMLLoader.load(getClass().getResource("/com/example/demo/" + fxmlFile));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

