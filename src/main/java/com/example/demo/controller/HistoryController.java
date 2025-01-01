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
    private TableColumn<Reservation, Integer> colWorkspaceId;
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
        colWorkspaceId.setCellValueFactory(new PropertyValueFactory<>("workspaceId"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        colStartTime.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateTimeStringConverter()));
        colEndTime.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateTimeStringConverter()));
        colWorkspaceId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//        colStatus.setCellFactory(TextFieldTableCell.forTableColumn());

        // Add the reservations to the table
        reservationTable.getItems().addAll(reservations);

        reservationTable.setEditable(true);

        // Handle edits
        colStartTime.setOnEditCommit(event -> {
            Reservation reservation = event.getRowValue();
            reservation.setStartTime(event.getNewValue());
            reservationService.updateReservation(reservation); // Call update method
        });

        colEndTime.setOnEditCommit(event -> {
            Reservation reservation = event.getRowValue();
            reservation.setEndTime(event.getNewValue());
            reservationService.updateReservation(reservation);
        });

        colWorkspaceId.setOnEditCommit(event -> {
            Reservation reservation = event.getRowValue();
            reservation.setWorkspaceId(event.getNewValue());
            reservationService.updateReservation(reservation);
        });

//        colStatus.setOnEditCommit(event -> {
//            Reservation reservation = event.getRowValue();
//            reservation.setStatus(event.getNewValue());
//            reservationService.updateReservation(reservation);
//        });
    }


//    private void populateFields(Reservation reservation) {
//        txtWorkspaceId.setText(String.valueOf(reservation.getWorkspaceId()));
//        dateStartTime.setValue(reservation.getStartTime().toLocalDate());
//        dateEndTime.setValue(reservation.getEndTime().toLocalDate());
//        comboStatus.setValue(reservation.getStatus());
//    }
}
