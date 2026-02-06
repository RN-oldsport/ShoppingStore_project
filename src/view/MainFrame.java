package view;

import model.Role;
import model.User;
import view.ProductManagementPanel;
import view.customer.CustomerProductPanel;
import view.authentication.AuthenticationPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainContainer;

    // Panels
    private AuthenticationPanel authenticationPanel;
    private ProductManagementPanel productManagementPanel;
    private CustomerProductPanel customerProductPanel;

    // Card names
    public static final String AUTH_PANEL = "AUTH_PANEL";
    public static final String ADMIN_PANEL = "ADMIN_PANEL";
    public static final String CUSTOMER_PANEL = "CUSTOMER_PANEL";

    private User currentUser;

    public MainFrame() {
        setTitle("Shopping Mall");
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        // Initialize panels
        authenticationPanel = new AuthenticationPanel(this);
        productManagementPanel = new ProductManagementPanel(this);
        customerProductPanel = new CustomerProductPanel(this);

        mainContainer.add(authenticationPanel, AUTH_PANEL);
        mainContainer.add(productManagementPanel, ADMIN_PANEL);
        mainContainer.add(customerProductPanel, CUSTOMER_PANEL);

        setContentPane(mainContainer);

        // Default panel: Authentication
        showPanel(AUTH_PANEL);

        setVisible(true);
    }

    // ------------------------
    // Switch Panel
    // ------------------------
    public void showPanel(String panelName) {
        cardLayout.show(mainContainer, panelName);
    }

    // ------------------------
    // Current User Handling
    // ------------------------
    public void setCurrentUser(User user) {
        this.currentUser = user;

        // Switch panel based on role
        if (user.getRole() == Role.ADMIN) {
            showPanel(ADMIN_PANEL);
        } else {
            showPanel(CUSTOMER_PANEL);
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    // ------------------------
    // Getters for Controllers
    // ------------------------
    public AuthenticationPanel getAuthenticationPanel() {
        return authenticationPanel;
    }

    public ProductManagementPanel getProductManagementPanel() {
        return productManagementPanel;
    }

    public CustomerProductPanel getCustomerProductPanel() {
        return customerProductPanel;
    }
}
