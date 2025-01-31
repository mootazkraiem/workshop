package com.example.demo.controller;

import com.example.demo.HelloApplication;
import com.example.demo.entity.CurrentUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    @FXML
    private StackPane contentPane;

    @FXML
    private VBox buttonContainer;

    @FXML
    private Button btnReservation;

    @FXML
    private Button btnHistory;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnLogout;

    @FXML
    private void initialize() {
        // Apply some CSS styling to buttons (optional)
        btnReservation.getStyleClass().add("dashboard-button");
        btnHistory.getStyleClass().add("dashboard-button");
        btnProfile.getStyleClass().add("dashboard-button");
        btnLogout.getStyleClass().add("dashboard-button");

        // Add listeners to buttons for navigation
        btnReservation.setOnAction(e -> navigateToReservation());
        btnHistory.setOnAction(e -> navigateToHistory());
        btnProfile.setOnAction(e -> navigateToProfile());
        btnLogout.setOnAction(e -> logout());
    }

    @FXML
    private void navigateToReservation() {
        loadView("reservation-view.fxml");  // No changes to the "reservation-view" file if it's still relevant
    }

    @FXML
    private void navigateToHistory() {
        loadView("history-view.fxml"); // This should be fine if it's not workspace-related
    }

    @FXML
    private void navigateToProfile() {
        loadView("profile-view.fxml"); // Update if needed to navigate to "profile" in a terrain-related context
    }

    @FXML
    private void logout() {
        CurrentUser currentUser = CurrentUser.getInstance();
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
