package com.example.demo.controller;

import com.example.demo.entity.Reservation;
import com.example.demo.service.ReservationService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateTimeStringConverter;

import java.time.LocalDateTime;
import java.util.List;

public class HistoryController {
    @FXML
    private TableView<Reservation> reservationTable;

    @FXML
    private TableColumn<Reservation, Integer> colId;

    @FXML
    private TableColumn<Reservation, LocalDateTime> colStartTime;

    @FXML
    private TableColumn<Reservation, LocalDateTime> colEndTime;

    @FXML
    private TableColumn<Reservation, Integer> colTerrainId; // Changed from colWorkspaceId to colTerrainId

    @FXML
    private TableColumn<Reservation, String> colStatus;

    private final ReservationService reservationService = new ReservationService();

    @FXML
    public void initialize() {
        List<Reservation> reservations = reservationService.getAllReservations();

        ObservableList<Reservation> observableReservations = FXCollections.observableArrayList(reservations);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        colTerrainId.setCellValueFactory(new PropertyValueFactory<>("terrainId")); // Changed from workspaceId to terrainId
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        colStartTime.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateTimeStringConverter()));
        colEndTime.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateTimeStringConverter()));
        colTerrainId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter())); // Changed from workspaceId to terrainId

        // Add the reservations to the table
        reservationTable.getItems().addAll(reservations);

        reservationTable.setEditable(true);

        // Handle edits
        colStartTime.setOnEditCommit(event -> {
            Reservation reservation = event.getRowValue();
            reservation.setStartTime(event.getNewValue());
            reservationService.updateReservation(reservation);
        });

        colEndTime.setOnEditCommit(event -> {
            Reservation reservation = event.getRowValue();
            reservation.setEndTime(event.getNewValue());
            reservationService.updateReservation(reservation);
        });

        colTerrainId.setOnEditCommit(event -> { // Changed from colWorkspaceId to colTerrainId
            Reservation reservation = event.getRowValue();
            reservation.setTerrainId(event.getNewValue()); // Changed from setWorkspaceId to setTerrainId
            reservationService.updateReservation(reservation);
        });

        // Optional: Uncomment and implement status edit if necessary
//        colStatus.setOnEditCommit(event -> {
//            Reservation reservation = event.getRowValue();
//            reservation.setStatus(event.getNewValue());
//            reservationService.updateReservation(reservation);
//        });
    }

}
