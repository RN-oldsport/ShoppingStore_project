package controller;

import services.AuthenticationService;
import model.User;
import view.MainFrame;
import view.authentication.AuthenticationPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthenticationController {

    private final MainFrame mainFrame;
    private final AuthenticationService authService;
    private final AuthenticationPanel authPanel;

    public AuthenticationController(MainFrame mainFrame, AuthenticationService authService) {
        this.mainFrame = mainFrame;
        this.authService = authService;
        this.authPanel = mainFrame.getAuthenticationPanel();

        connectLoginButton();
        connectSignupButton();
    }

    private void connectLoginButton() {
        authPanel.getBtnLogin().addActionListener(e -> {
            String username = authPanel.getTxtUsername().getText();
            String password = new String(authPanel.getTxtPassword().getPassword());

            User user = authService.login(username, password).getData();

            if (user != null) {
                authPanel.getLblMessage().setText("Login successful!");
                mainFrame.setCurrentUser(user); // اینجا تعیین می‌کنیم Admin یا Customer
            } else {
                authPanel.getLblMessage().setText("Invalid credentials!");
            }
        });
    }

    private void connectSignupButton() {
        authPanel.getBtnSignup().addActionListener(e -> {
            String username = authPanel.getTxtUsername().getText();
            String password = new String(authPanel.getTxtPassword().getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                authPanel.getLblMessage().setText("Username and password required!");
                return;
            }

            User newUser = authService.signup(username, password).getData();

            if (newUser != null) {
                authPanel.getLblMessage().setText("Signup successful! You can login now.");
            } else {
                authPanel.getLblMessage().setText("Username already exists!");
            }
        });
    }
}
