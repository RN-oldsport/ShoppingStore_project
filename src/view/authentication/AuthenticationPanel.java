package view.authentication;

import view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class AuthenticationPanel extends JPanel {

    private MainFrame mainFrame;

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnSignup;

    private JLabel lblMessage;

    public AuthenticationPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblUsername = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblUsername, gbc);

        txtUsername = new JTextField(20);
        gbc.gridx = 1;
        add(txtUsername, gbc);

        JLabel lblPassword = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblPassword, gbc);

        txtPassword = new JPasswordField(20);
        gbc.gridx = 1;
        add(txtPassword, gbc);

        btnLogin = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(btnLogin, gbc);

        btnSignup = new JButton("Signup");
        gbc.gridx = 1;
        add(btnSignup, gbc);

        lblMessage = new JLabel(" ");
        lblMessage.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(lblMessage, gbc);
    }


    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JButton getBtnSignup() {
        return btnSignup;
    }

    public JLabel getLblMessage() {
        return lblMessage;
    }
}
