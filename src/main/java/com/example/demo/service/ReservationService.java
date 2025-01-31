package com.example.demo.service;

import com.example.demo.entity.CurrentUser;
import com.example.demo.entity.Reservation;
import com.example.demo.util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {

    public List<Reservation> reservations = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        String sql = "INSERT INTO reservations (terrain_id, user_id, start_time, end_time, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Set parameters for the SQL query
            preparedStatement.setInt(1, reservation.getTerrainId());
            preparedStatement.setInt(2, reservation.getUserId()); // Replace with actual user ID if needed
            preparedStatement.setString(3, reservation.getStartTime().toString());
            preparedStatement.setString(4, reservation.getEndTime().toString());
            preparedStatement.setString(5, reservation.getStatus());

            // Execute the SQL insert statement
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add reservation", e);
        }
    }

    public void updateReservation(Reservation reservation) {
        String sql = "UPDATE reservations SET start_time = ?, end_time = ?, terrain_id = ?, status = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(reservation.getStartTime()));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(reservation.getEndTime()));
            preparedStatement.setInt(3, reservation.getTerrainId());
            preparedStatement.setString(4, reservation.getStatus());
            preparedStatement.setInt(5, reservation.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteReservation(int reservationId) {
        String sql = "DELETE FROM reservations WHERE id = " + reservationId;
        try(Connection connection = DatabaseConnection.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Reservation> getAllReservations() {
        // Get the logged-in user's ID
        CurrentUser currentUser = CurrentUser.getInstance();
        int userId = currentUser.getId(); // Assuming currentUser.getId() returns the logged-in user's ID

        // Modify the SQL query to get only the reservations for the logged-in user
        String sql = "SELECT * FROM reservations WHERE user_id = ?";

        List<Reservation> reservations = new ArrayList<>();

        try (Connection connection = DatabaseConnection.connect()) {
            // Prepare the statement and set the user ID
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId); // Set the user_id in the query

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Process the result set and add reservations to the list
                while (resultSet.next()) {
                    // Retrieve values from the result set and map them to the Reservation object
                    int id = resultSet.getInt("id");
                    LocalDateTime startTime = resultSet.getTimestamp("start_time").toLocalDateTime();
                    LocalDateTime endTime = resultSet.getTimestamp("end_time").toLocalDateTime();
                    int userIdFromDb = resultSet.getInt("user_id");
                    int terrainId = resultSet.getInt("terrain_id");
                    String status = resultSet.getString("status");

                    // Create a new Reservation object
                    Reservation reservation = new Reservation(id, startTime, endTime, userIdFromDb, terrainId, status);
                    reservations.add(reservation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return reservations;
    }

    public List<Reservation> getAllReservationsForAdmin() {
        // Get the logged-in user's ID
        CurrentUser currentUser = CurrentUser.getInstance();
        int userId = currentUser.getId(); // Assuming currentUser.getId() returns the logged-in user's ID

        // Modify the SQL query to get all reservations (for admin view)
        String sql = "SELECT * FROM reservations";

        List<Reservation> reservations = new ArrayList<>();

        try (Connection connection = DatabaseConnection.connect()) {
            // Prepare the statement and set the user ID
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Process the result set and add reservations to the list
                while (resultSet.next()) {
                    // Retrieve values from the result set and map them to the Reservation object
                    int id = resultSet.getInt("id");
                    LocalDateTime startTime = resultSet.getTimestamp("start_time").toLocalDateTime();
                    LocalDateTime endTime = resultSet.getTimestamp("end_time").toLocalDateTime();
                    int userIdFromDb = resultSet.getInt("user_id");
                    int terrainId = resultSet.getInt("terrain_id");
                    String status = resultSet.getString("status");

                    // Create a new Reservation object
                    Reservation reservation = new Reservation(id, startTime, endTime, userIdFromDb, terrainId, status);
                    reservations.add(reservation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return reservations;
    }
}
