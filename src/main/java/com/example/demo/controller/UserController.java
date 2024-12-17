package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class UserController {
    private final UserService userService = new UserService();

    @FXML
    private TextField idField, nameField, emailField, passwordField, roleField;

    @FXML
    private TextArea outputArea;

    @FXML
    private Button addButton, viewButton, updateButton, deleteButton, loginButton;

    @FXML
    public void initialize() {
        addButton.setOnAction(e -> addUser());
        viewButton.setOnAction(e -> viewAllUsers());
        updateButton.setOnAction(e -> updateUser());
        deleteButton.setOnAction(e -> deleteUser());
        loginButton.setOnAction(e -> loginUser());
    }

    private void addUser() {
        User user = new User();
        user.setName(nameField.getText());
        user.setEmail(emailField.getText());
        user.setPassword(passwordField.getText());
        user.setRole(roleField.getText());

        userService.addUser(user);
        outputArea.setText("User added successfully!");
        clearFields();
    }

    private void viewAllUsers() {
        List<User> users = userService.getAllUsers();
        StringBuilder output = new StringBuilder("All Users:\n");
        for (User user : users) {
            output.append("ID: ").append(user.getId())
                    .append(", Name: ").append(user.getName())
                    .append(", Email: ").append(user.getEmail())
                    .append(", Role: ").append(user.getRole()).append("\n");
        }
        outputArea.setText(output.toString());
    }

    private void updateUser() {
        User user = new User();
        user.setId(Integer.parseInt(idField.getText()));
        user.setName(nameField.getText());
        user.setEmail(emailField.getText());
        user.setPassword(passwordField.getText());
        user.setRole(roleField.getText());

        userService.updateUser(user);
        outputArea.setText("User updated successfully!");
        clearFields();
    }

    private void deleteUser() {
        int userId = Integer.parseInt(idField.getText());
        userService.deleteUser(userId);
        outputArea.setText("User deleted successfully!");
        clearFields();
    }

    private void loginUser() {
        String email = emailField.getText();
        String password = passwordField.getText();
        //Stage stage = (Stage) this.getClass().getClassLoader().getResource("com/example/demo/login-view.fxml").getPath();

//        User user = userService.loginUser(email, password);
//        if (user != null) {
//            outputArea.setText("Login successful! Welcome, " + user.getName());
//        } else {
//            outputArea.setText("Login failed: Invalid email or password.");
//        }
//        clearFields();
    }

    private void clearFields() {
        idField.clear();
        nameField.clear();
        emailField.clear();
        passwordField.clear();
        roleField.clear();
    }
}
