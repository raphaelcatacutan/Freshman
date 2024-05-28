package com.plm.studentdb.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class ViewLogin {
    @FXML
    private Label lbUsername;
    @FXML
    private Label lbPassword;
    @FXML
    private Label loginStatus;
    @FXML
    private TextField userID;
    @FXML
    private PasswordField userPassword;
    @FXML
    private Button loginButton;

    private final String predefinedUsername = "admin@plm.edu.ph";
    private final String predefinedPassword = "123456";

    @FXML
    public void logInButton() {
        String enteredUsername = userID.getText();
        String enteredPassword = userPassword.getText();

        if (predefinedUsername.equals(enteredUsername) && predefinedPassword.equals(enteredPassword)) {
            loginStatus.setText("Login Successful!");
        } else {
            loginStatus.setText("Invalid Username or Password!");
        }
    }
}