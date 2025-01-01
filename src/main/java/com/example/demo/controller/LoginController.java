package com.example.demo.controller;

import com.example.demo.HelloApplication;
import com.example.demo.entity.CurrentUser;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    private final UserService userService =new UserService();


    Stage stage;
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    // These fields should be annotated with @FXML to link them to the FXML elements
    @FXML
    private TextField usernameField;  // TextField for username input

    @FXML
    private PasswordField passwordField;  // PasswordField for password input

    @FXML
    private Button loginButton;  // Login button

    @FXML
    private Label statusLabel;  // Label to show login status

    // This method is called when the login button is clicked
    @FXML
    private void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        User user = userService.loginUser(username, password);

        if ( user != null) {
            // Pass the logged-in user ID to the controller

            CurrentUser currentUser = CurrentUser.getInstance();
            currentUser.setId(user.getId());
            currentUser.setUsername(user.getName());
            currentUser.setPassword(user.getPassword());
            currentUser.setRole(user.getRole());
            currentUser.setEmail(user.getEmail());


            statusLabel.setText("Login successful!");
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dashboard-view.fxml"));
                Parent dashboardRoot = fxmlLoader.load();
                Scene dashboardScene = new Scene(dashboardRoot, 800, 600);

                // Get the current stage dynamically
                Stage stage = (Stage) ((Node) usernameField).getScene().getWindow();

                // Set the new scene
                stage.setScene(dashboardScene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                statusLabel.setText("Error: Unable to load the dashboard.");
            }
        } else {
            statusLabel.setText("Login failed: Invalid username or password.");
        }
    }

    // This method will be called to initialize the controller
    @FXML
    public void initialize() {
        // You can initialize things here if necessary
        usernameField.setText("mootazfsg@gmail.com");
        passwordField.setText("mootaz123");
    }
}
