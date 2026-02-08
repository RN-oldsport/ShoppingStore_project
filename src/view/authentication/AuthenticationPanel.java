package view.authentication;

import view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class AuthenticationPanel extends JPanel {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnSignup;

    private JLabel lblMessage;

    public AuthenticationPanel(MainFrame mainFrame) {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font normalFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);
        Font messageFont = new Font("Segoe UI", Font.PLAIN, 12);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(normalFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblUsername, gbc);

        txtUsername = new JTextField(20);
        txtUsername.setFont(normalFont);
        txtUsername.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(200, 200, 200)),
                        BorderFactory.createEmptyBorder(6, 10, 6, 10)
                )
        );
        gbc.gridx = 1;
        add(txtUsername, gbc);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(normalFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblPassword, gbc);

        txtPassword = new JPasswordField(20);
        txtPassword.setFont(normalFont);
        txtPassword.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(200, 200, 200)),
                        BorderFactory.createEmptyBorder(6, 10, 6, 10)
                )
        );
        gbc.gridx = 1;
        add(txtPassword, gbc);

        // Signup Button
        btnSignup = new JButton("Signup");
        styleButton(btnSignup, buttonFont, new Color(190, 240, 240));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(btnSignup, gbc);

        // Login Button
        btnLogin = new JButton("Login");
        styleButton(btnLogin, buttonFont, new Color(190, 240, 240));
        gbc.gridx = 1;
        add(btnLogin, gbc);

        // Message Label
        lblMessage = new JLabel(" ");
        lblMessage.setForeground(Color.RED);
        lblMessage.setFont(messageFont);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblMessage, gbc);
    }

    private void styleButton(JButton btn, Font font, Color bg) {
        btn.setFont(font);
        btn.setForeground(Color.DARK_GRAY);
        btn.setBackground(bg);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorder(
                BorderFactory.createEmptyBorder(10, 24, 10, 24)
        );
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
