package com.example.demo.service;

import com.example.demo.entity.Reservation;
import com.example.demo.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {

    public List<Reservation> reservations = new ArrayList<>();


    public void addReservation(Reservation reservation) {
        String sql = "INSERT INTO reservations (workspace_id, user_id, start_time, end_time, status) VALUES ('" + reservation.getWorkspaceId() + "', '" + reservation.getUserId() + "', '" + reservation.getStartTime() + "', '" + reservation.getEndTime() + "')";
        try(Connection connection = DatabaseConnection.connect())
            {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();



            }catch (Exception e){
                e.printStackTrace();
            }
    }

    public void updateReservation(Reservation reservation) {
        String sql = "UPDATE reservations SET workspace_id = '" + reservation.getWorkspaceId() + "', user_id = '" + reservation.getUserId() + "', start_time = '" + reservation.getStartTime() + "', end_time = '" + reservation.getEndTime() + "' WHERE id = " + reservation.getId();
        try(Connection connection = DatabaseConnection.connect())
            {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();



            }catch (Exception e){
                e.printStackTrace();
            }

    }

    public void deleteReservation(int reservationId) {
        String sql = "DELETE FROM reservations WHERE id = " + reservationId;
        try(Connection connection = DatabaseConnection.connect())
            {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();





            }catch (Exception e){
                e.printStackTrace();
            }
    }

    public List<Reservation> getallreservations(){

        String sql = "SELECT * FROM reservations";
        try(Connection connection = DatabaseConnection.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            try (ResultSet statement = preparedStatement.executeQuery()) {

                return reservations;

            }


        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }


}
