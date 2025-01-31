package com.example.demo.controller;


import com.example.demo.entity.Reservation;

import com.example.demo.service.ReservationService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;

import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateTimeStringConverter;

import java.io.IOException;
import java.time.LocalDateTime;

import java.util.List;

public class AdminReservationController {
    @FXML
    private TableView<Reservation> reservationTable;
    @FXML
    private TableColumn<Reservation, Integer> colId;
    @FXML
    private TableColumn<Reservation, LocalDateTime> colStartTime;
    @FXML
    private TableColumn<Reservation, LocalDateTime> colEndTime;
    @FXML
    private TableColumn<Reservation, Integer> colTicketId;
    @FXML
    private TableColumn<Reservation, String> colStatus;
    @FXML
    private TableColumn<Reservation, Integer> colUserId;
    @FXML
    private Button add;



    private final ReservationService reservationService = new ReservationService();
    @FXML
    public void initialize() {
        List<Reservation> reservations = reservationService.getAllReservationsforAdmin();

        ObservableList<Reservation> observableReservations = FXCollections.observableArrayList(reservations);


        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        colTicketId.setCellValueFactory(new PropertyValueFactory<>("TicketId"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));

        colStartTime.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateTimeStringConverter()));
        colEndTime.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateTimeStringConverter()));
        colTicketId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colStatus.setCellFactory(TextFieldTableCell.forTableColumn());
        colUserId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));


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

        colTicketId.setOnEditCommit(event -> {
            Reservation reservation = event.getRowValue();
            reservation.setTicketId(event.getNewValue());
            reservationService.updateReservation(reservation);
        });

        colStatus.setOnEditCommit(event -> {
            Reservation reservation = event.getRowValue();
            reservation.setStatus(event.getNewValue());
            reservationService.updateReservation(reservation);
        });

        colUserId.setOnEditCommit(event -> {
            Reservation reservation = event.getRowValue();
            reservation.setUserId(event.getNewValue());
            reservationService.updateReservation(reservation);
        });
    }
    public void add(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/reservation-view.fxml"));
            Parent addReservationView = loader.load();


            StackPane contentPane = (StackPane) reservationTable.getScene().lookup("#contentPane");


            contentPane.getChildren().clear();
            contentPane.getChildren().add(addReservationView);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
