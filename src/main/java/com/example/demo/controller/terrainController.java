package com.example.demo.controller;

import com.example.demo.entity.terrain; // Changed from Workspace to terrain
import com.example.demo.service.terrainService; // Changed from WorkspaceService to terrainService
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.List;

public class terrainController { // Changed from workspaceController to terrainController
    @FXML
    private TableView<terrain> terrainTable; // Changed from workspaceTable to terrainTable

    @FXML
    private TableColumn<terrain, Integer> colId; // Changed from colId to colId for terrain

    @FXML
    private TableColumn<terrain, String> colName; // Changed from colName to colName for terrain

    @FXML
    private TableColumn<terrain, String> colLocation; // Changed from colLocation to colLocation for terrain

    @FXML
    private TableColumn<terrain, Integer> colCapacity; // Changed from colCapacity to colCapacity for terrain

    @FXML
    private TableColumn<terrain, String> colStatus; // Changed from colStatus to colStatus for terrain

    @FXML
    private Button addterrainButton; // Changed from addWorkspaceButton to addterrainButton

    private final terrainService terrainService = new terrainService(); // Changed from WorkspaceService to terrainService

    @FXML
    public void initialize() {
        List<terrain> terrains = terrainService.getAllTerrains(); // Changed from getAllWorkspaces to getAllterrains
        ObservableList<terrain> observableterrains = FXCollections.observableArrayList(terrains);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("availability_status"));

        terrainTable.setItems(observableterrains); // Changed from workspaceTable to terrainTable
    }

    @FXML
    private void handleAddterrainAction() { // Changed from handleAddWorkspaceAction to handleAddterrainAction
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/add-terrain-view.fxml")); // Changed from add-terrain-view.fxml to add-terrain-view.fxml
            Parent addterrainView = loader.load();

            StackPane contentPane = (StackPane) terrainTable.getScene().lookup("#contentPane"); // Changed from workspaceTable to terrainTable
            contentPane.getChildren().clear();
            contentPane.getChildren().add(addterrainView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
