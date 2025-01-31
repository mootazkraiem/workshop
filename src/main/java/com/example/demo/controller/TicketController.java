package com.example.demo.controller;

import com.example.demo.entity.Ticket;
import com.example.demo.service.TicketService;
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

public class TicketController {
    @FXML
    private TableView<Ticket> TicketTable;

    @FXML
    private TableColumn<Ticket, Integer> colId;

    @FXML
    private TableColumn<Ticket, String> colName;

    @FXML
    private TableColumn<Ticket, String> colLocation;

    @FXML
    private TableColumn<Ticket, Integer> colCapacity;

    @FXML
    private TableColumn<Ticket, String> colStatus;

    @FXML
    private Button addTicketButton;



    private final TicketService TicketService = new TicketService();

    @FXML
    public void initialize() {
        List<Ticket> Tickets = TicketService.getAllTickets();
        ObservableList<Ticket> observableTickets = FXCollections.observableArrayList(Tickets);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("availability_status"));

        TicketTable.setItems(observableTickets);
    }

    @FXML
    private void handleAddTicketAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/add-Ticket-view.fxml"));
            Parent addTicketView = loader.load();

            StackPane contentPane = (StackPane) TicketTable.getScene().lookup("#contentPane");
            contentPane.getChildren().clear();
            contentPane.getChildren().add(addTicketView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
