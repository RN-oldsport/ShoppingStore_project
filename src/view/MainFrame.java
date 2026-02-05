package view;

import model.Product;
import view.ProductManagementPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainContainer;

    // panel names (like IDs)
    public static final String PRODUCT_PANEL = "PRODUCT_PANEL";

    // Panels
    private ProductManagementPanel productManagementPanel;


    public MainFrame() {

        setTitle("Shopping Mall");
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        productManagementPanel = new ProductManagementPanel(this);

        mainContainer.add(productManagementPanel, PRODUCT_PANEL);

        setContentPane(mainContainer);

        // first screen
        showPanel(PRODUCT_PANEL);

        setVisible(true);

        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1, "Mouse", "Tech", 20000, 5, "Gaming Mouse", "C:\\Users\\cyb\\ShoppingStore_project\\data\\product_images\\w08161-wdq.jpg"));
        products.add(new Product(2, "Keyboard", "Tech", 120000, 3, "Mechanical Keyboard", "C:\\Users\\cyb\\Pictures\\Saved Pictures\\Snake-Blue-viper.jpg"));

        productManagementPanel.refreshProducts(products);
    }




    // Switch panels
    public void showPanel(String panelName) {
        cardLayout.show(mainContainer, panelName);
    }

    // Getters (controller or panels can use them)
    public ProductManagementPanel getProductManagementPanel() {
        return productManagementPanel;
    }

}
