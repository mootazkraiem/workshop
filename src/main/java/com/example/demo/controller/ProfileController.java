package com.example.demo.controller;

import com.example.demo.entity.CurrentUser;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class ProfileController {
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtRole;
    @FXML
    private Label statusLabel;
    @FXML
    private TextField confirmedPassword;

    private final UserService userService = new UserService(); // Assumes you have a UserService class

    @FXML
    public void initialize() {

        CurrentUser currentUser = CurrentUser.getInstance();
        if (currentUser != null) {
            txtName.setText(currentUser.getUsername());
            txtEmail.setText(currentUser.getEmail());
//            txtPassword.setText(currentUser.getPassword());
            txtRole.setText(currentUser.getRole());
        }
    }


    @FXML
    private void handleSave() {
        if(txtEmail.getText().isEmpty() ) {
            statusLabel.setText("Please enter your email");
            return;
        }

        if(txtRole.getText().isEmpty() ) {
            statusLabel.setText("Please enter your role");
            return;

        }

        if(txtName.getText().isEmpty() ) {
            statusLabel.setText("Please enter your name");
            return;

        }
        if (txtPassword.getText().length() != 0 ) {


            if (!txtPassword.getText().equals(confirmedPassword.getText())) {
                statusLabel.setText("Passwords do not match");

                return;
            }
        }







        CurrentUser currentUser = CurrentUser.getInstance();

        if (currentUser != null) {
            currentUser.setUsername(txtName.getText());
            currentUser.setEmail(txtEmail.getText());

            if(!txtPassword.getText().isEmpty())
            {
                currentUser.setPassword(txtPassword.getText());
            }


            currentUser.setRole(txtRole.getText());

            User newUser = new User();
            newUser.setEmail(CurrentUser.getInstance().getEmail());
            newUser.setPassword(CurrentUser.getInstance().getPassword());
            newUser.setRole(CurrentUser.getInstance().getRole());
            newUser.setName(CurrentUser.getInstance().getUsername());
            newUser.setId(CurrentUser.getInstance().getId());


            // Save the updated user details in the database
            userService.updateUser(newUser);

            // Close the profile window
//            Stage stage = (Stage) txtName.getScene().getWindow();
//            stage.close();
            statusLabel.setText("Successfully updated");
        }
    }
}
