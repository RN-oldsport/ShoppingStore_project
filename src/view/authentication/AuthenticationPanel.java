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

        JLabel lblUsername = new JLabel("Username:");
        // بزرگ‌تر کردن فونت لیبل بدون تغییر نوع فونت
        lblUsername.setFont(lblUsername.getFont().deriveFont(14f));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblUsername, gbc);

        txtUsername = new JTextField(20);
        // بزرگ‌تر کردن فونت فیلد ورودی بدون تغییر فونت
        txtUsername.setFont(txtUsername.getFont().deriveFont(14f));
        gbc.gridx = 1;
        add(txtUsername, gbc);

        JLabel lblPassword = new JLabel("Password:");
        // بزرگ‌تر کردن فونت لیبل پسورد بدون تغییر فونت
        lblPassword.setFont(lblPassword.getFont().deriveFont(14f));
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblPassword, gbc);

        txtPassword = new JPasswordField(20);
        // بزرگ‌تر کردن فونت فیلد پسورد بدون تغییر فونت
        txtPassword.setFont(txtPassword.getFont().deriveFont(14f));
        gbc.gridx = 1;
        add(txtPassword, gbc);

        btnSignup = new JButton("Signup");
        btnSignup.setBackground(new Color(190, 240, 240));
        // بزرگ‌تر کردن فونت دکمه ثبت‌نام بدون تغییر فونت
        btnSignup.setFont(btnSignup.getFont().deriveFont(14f));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(btnSignup, gbc);

        btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(190, 240, 240));
        // بزرگ‌تر کردن فونت دکمه ورود بدون تغییر فونت
        btnLogin.setFont(btnLogin.getFont().deriveFont(14f));
        gbc.gridx = 1;
        add(btnLogin, gbc);

        lblMessage = new JLabel(" ");
        lblMessage.setForeground(Color.RED);
        // کمی بزرگ‌تر کردن فونت پیام (ولی هنوز ظریف‌تر از بقیه)
        lblMessage.setFont(lblMessage.getFont().deriveFont(14f));
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
