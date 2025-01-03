package com.example.demo.controller;

import com.example.demo.entity.Workspace;
import com.example.demo.service.WorkspaceService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.List;

public class WorkspaceController {
    @FXML
    private TableView<Workspace> workspaceTable;

    @FXML
    private TableColumn<Workspace, Integer> colId;

    @FXML
    private TableColumn<Workspace, String> colName;

    @FXML
    private TableColumn<Workspace, String> colLocation;

    @FXML
    private TableColumn<Workspace, Integer> colCapacity;

    @FXML
    private TableColumn<Workspace, String> colStatus;

    @FXML
    private Button addWorkspaceButton;



    private final WorkspaceService workspaceService = new WorkspaceService();

    @FXML
    public void initialize() {
        List<Workspace> workspaces = workspaceService.getAllWorkspaces();
        ObservableList<Workspace> observableWorkspaces = FXCollections.observableArrayList(workspaces);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("availability_status"));

        workspaceTable.setItems(observableWorkspaces);
    }

    @FXML
    private void handleAddWorkspaceAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/add-workspace-view.fxml"));
            Parent addWorkspaceView = loader.load();

            StackPane contentPane = (StackPane) workspaceTable.getScene().lookup("#contentPane");
            contentPane.getChildren().clear();
            contentPane.getChildren().add(addWorkspaceView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
