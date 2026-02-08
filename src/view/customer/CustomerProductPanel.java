package view.customer;

import model.Customer;
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
    private JLabel lblBalance;
    private JButton btnlogout;
    private Customer customer;

    public CustomerProductPanel(MainFrame mainFrame) {

        setLayout(new BorderLayout());

        cards = new ArrayList<>();

        productsContainerPanel = new JPanel();
        productsContainerPanel.setLayout(new BoxLayout(productsContainerPanel, BoxLayout.Y_AXIS));
        productsContainerPanel.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(productsContainerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        btnViewCart = new JButton("View Cart");
        btnViewCart.setFont(btnViewCart.getFont().deriveFont(14f));

        btnPurchase = new JButton("Purchase");
        btnPurchase.setFont(btnPurchase.getFont().deriveFont(14f));

        btnlogout = new JButton("Logout");
        btnlogout.setFont(btnlogout.getFont().deriveFont(14f));

        btnViewCart.setBackground(new Color(190, 240, 240));
        btnPurchase.setBackground(new Color(190, 240, 240));
        btnlogout.setBackground(new Color(240, 170, 190));

        customer = (Customer) mainFrame.getCurrentUser();

        lblBalance = new JLabel("Balance : ");
        lblBalance.setFont(lblBalance.getFont().deriveFont(14f));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 25, 5));
        topPanel.add(btnViewCart);
        topPanel.add(btnPurchase);
        topPanel.add(lblBalance);
        topPanel.add(btnlogout);

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

    public JLabel getLblBalance() {
        return lblBalance;
    }

    public JButton getBtnLogout() {
        return btnlogout;
    }
}
