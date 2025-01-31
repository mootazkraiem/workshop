package com.example.demo.controller;

import com.example.demo.entity.Status;
import com.example.demo.entity.terrain; // Changed from Workspace to Terrain
import com.example.demo.service.terrainService; // Changed from WorkspaceService to TerrainService
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class AddterrainController { // Changed AddworkspaceController to AddTerrainController
    @FXML
    private TextField nameField;

    @FXML
    private TextField locationField;

    @FXML
    private TextField capacityField;

    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private Button submitButton;

    @FXML
    private Button backButton;

    private final terrainService terrainService = new terrainService(); // Changed WorkspaceService to TerrainService

    @FXML
    public void initialize() {
        statusComboBox.getItems().addAll("Available", "Unavailable");
    }

    @FXML
    private void handleSubmitAction() {
        String name = nameField.getText();
        String location = locationField.getText();
        int capacity = Integer.parseInt(capacityField.getText());
        String status = statusComboBox.getValue();

        terrain terrain = new terrain(); // Changed Workspace to Terrain
        terrain.setName(name); // Changed workspace to terrain
        terrain.setLocation(location); // Changed workspace to terrain
        terrain.setCapacity(capacity); // Changed workspace to terrain
        terrain.setAvailability_status(Status.valueOf(status.toUpperCase())); // Changed workspace to terrain

        terrainService.addTerrain(terrain); // Changed addWorkspace to addTerrain
    }

    @FXML
    private void handleBackAction() {
        try {
            // Load the previous view (e.g., adminReservation-view.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/terrain-view.fxml")); // Changed terrain-view.fxml to terrain-view.fxml
            Parent adminReservationView = loader.load();

            // Get the content pane from the dashboard
            StackPane contentPane = (StackPane) statusComboBox.getScene().lookup("#contentPane");

            // Replace the current view in the content pane
            contentPane.getChildren().clear();
            contentPane.getChildren().add(adminReservationView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
