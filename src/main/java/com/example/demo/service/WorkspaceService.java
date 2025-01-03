package com.example.demo.service;

import com.example.demo.entity.Status;
import com.example.demo.entity.Workspace;
import com.example.demo.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkspaceService {

    private List<Workspace> workspaces = new ArrayList<>();

    public List<Workspace> getAllWorkspaces(){
        List<Workspace> workspaces = new ArrayList<>();
        String query = "SELECT * FROM workspace";

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


                Workspace workspace = new Workspace(id, name, location, capacity, availability_status);

                workspaces.add(workspace);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return workspaces;
    }



    public void addWorkspace(Workspace workspace) {
        // SQL query to insert a new workspace
        String sql = "INSERT INTO workspace (name, location, capacity, availability_status) VALUES (?, ?, ?, ?)";

        // Try-with-resources to ensure that resources are closed properly
        try (Connection connection = DatabaseConnection.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            // Set the parameters for the prepared statement
            preparedStatement.setString(1, workspace.getName());
            preparedStatement.setString(2, workspace.getLocation());
            preparedStatement.setInt(3, workspace.getCapacity());
            preparedStatement.setString(4, workspace.getAvailability_status().name());

            preparedStatement.executeUpdate();



        } catch (SQLException e) {
            // Print any SQL exceptions
            System.out.println("Error while adding workspace: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteWorkspace(int workspaceId) {
            workspaces.removeIf(workspace -> workspace.getId() == workspaceId);
    }
}
