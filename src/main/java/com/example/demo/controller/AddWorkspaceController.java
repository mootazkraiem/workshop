package com.example.demo.controller;

import com.example.demo.entity.Status;
import com.example.demo.entity.Workspace;
import com.example.demo.service.WorkspaceService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class AddWorkspaceController {
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

    private final WorkspaceService workspaceService = new WorkspaceService();

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

        Workspace workspace = new Workspace();
        workspace.setName(name);
        workspace.setLocation(location);
        workspace.setCapacity(capacity);
        workspace.setAvailability_status(Status.valueOf(status.toUpperCase()));

        workspaceService.addWorkspace(workspace);

    }

    @FXML
    private void handleBackAction() {
        try {
            // Load the previous view (e.g., adminReservation-view.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/workspace-view.fxml"));
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
