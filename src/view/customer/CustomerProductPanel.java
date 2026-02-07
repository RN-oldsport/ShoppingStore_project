package view.customer;

import model.Product;
import view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerProductPanel extends JPanel {

    private JPanel productsContainerPanel;
    private JScrollPane scrollPane;

    private List<CustomerProductCardPanel> cards;

    private JButton btnViewCart;
    private JButton btnPurchase;

    public CustomerProductPanel(MainFrame mainFrame) {

        setLayout(new BorderLayout());

        cards = new ArrayList<>();

        productsContainerPanel = new JPanel();
        productsContainerPanel.setLayout(new BoxLayout(productsContainerPanel, BoxLayout.Y_AXIS));
        productsContainerPanel.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(productsContainerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        btnViewCart = new JButton("View Cart");
        btnPurchase = new JButton("Purchase");

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(btnViewCart);
        topPanel.add(btnPurchase);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void refreshProducts(List<Product> products) {

        productsContainerPanel.removeAll();
        cards.clear();

        for (Product p : products) {
            CustomerProductCardPanel card = new CustomerProductCardPanel(p);
            cards.add(card);

            productsContainerPanel.add(card);
            productsContainerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        productsContainerPanel.revalidate();
        productsContainerPanel.repaint();
    }

    public List<CustomerProductCardPanel> getCards() {
        return cards;
    }

    public JButton getBtnViewCart() {
        return btnViewCart;
    }

    public JButton getBtnPurchase() {
        return btnPurchase;
    }
}
