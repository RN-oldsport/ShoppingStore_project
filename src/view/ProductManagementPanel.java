package view;

import model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProductManagementPanel extends JPanel {

    private JPanel productsContainerPanel;
    private JScrollPane scrollPane;


    public ProductManagementPanel(MainFrame mainFrame) {

        setLayout(new BorderLayout());

        // Container panel for product cards
        productsContainerPanel = new JPanel();
        productsContainerPanel.setLayout(new BoxLayout(productsContainerPanel, BoxLayout.Y_AXIS));
        productsContainerPanel.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(productsContainerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane, BorderLayout.CENTER);
    }


    // IMPORTANT: refresh list
    public void refreshProducts(List<Product> products) {

        productsContainerPanel.removeAll();

        for (Product p : products) {
            ProductCardPanel card = new ProductCardPanel(p);

            productsContainerPanel.add(card);
            productsContainerPanel.add(Box.createRigidArea(new Dimension(0, 10))); // فاصله بین کارت‌ها
        }

        productsContainerPanel.revalidate();
        productsContainerPanel.repaint();
    }
}
