package com.example.demo.controller;

import com.example.demo.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    private final UserService userService =new UserService();



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
        // Get the values entered by the user
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Simple login validation (you can replace this with actual authentication logic)
        if (userService.loginUser(username, password)!=null){
            statusLabel.setText("Login successful!");
        }
        else{
            statusLabel.setText("Login failed: Invalid username or password.");
        }
    }

    // This method will be called to initialize the controller
    @FXML
    public void initialize() {
        // You can initialize things here if necessary
    }
}
