package com.example.demo.service;

import com.example.demo.entity.Status;
import com.example.demo.entity.Ticket;
import com.example.demo.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketService {

    private List<Ticket> Tickets = new ArrayList<>();

    public List<Ticket> getAllTickets(){
        List<Ticket> Tickets = new ArrayList<>();
        String query = "SELECT * FROM Ticket";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String location = resultSet.getString("location");
                int capacity = resultSet.getInt("capacity");
                String statusString = resultSet.getString("availability_status");

                Status availability_status = Status.valueOf(statusString.toUpperCase());


                Ticket Ticket = new Ticket(id, name, location, capacity, availability_status);

                Tickets.add(Ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Tickets;
    }



    public void addTicket(Ticket Ticket) {
        // SQL query to insert a new Ticket
        String sql = "INSERT INTO Ticket (name, location, capacity, availability_status) VALUES (?, ?, ?, ?)";

        // Try-with-resources to ensure that resources are closed properly
        try (Connection connection = DatabaseConnection.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            // Set the parameters for the prepared statement
            preparedStatement.setString(1, Ticket.getName());
            preparedStatement.setString(2, Ticket.getLocation());
            preparedStatement.setInt(3, Ticket.getCapacity());
            preparedStatement.setString(4, Ticket.getAvailability_status().name());

            preparedStatement.executeUpdate();



        } catch (SQLException e) {
            // Print any SQL exceptions
            System.out.println("Error while adding Ticket: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteTicket(int TicketId) {
        Tickets.removeIf(Ticket -> Ticket.getId() == TicketId);
    }
}
