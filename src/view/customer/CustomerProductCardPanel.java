package view.customer;

import model.Product;

import javax.swing.*;
import java.awt.*;

public class CustomerProductCardPanel extends JPanel {

    private Product product;

    private JLabel lblImage;
    private JLabel lblTitle;
    private JLabel lblPrice;
    private JLabel lblStock;
    private JLabel lblCategory;
    private JLabel lblDescription;

    private JButton btnAddToCart;

    public CustomerProductCardPanel(Product product) {
        this.product = product;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        setPreferredSize(new Dimension(800, 200));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 220));
        setBackground(new Color(240, 240, 240));

        initComponents();
        buildUI();
    }

    private void initComponents() {

        lblImage = new JLabel();
        lblImage.setPreferredSize(new Dimension(180, 180));
        lblImage.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);

        if (product.getImage() != null && !product.getImage().isEmpty()) {
            ImageIcon icon = new ImageIcon(product.getImage());
            Image img = icon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            lblImage.setIcon(new ImageIcon(img));
        } else {
            lblImage.setText("No Image");
            lblImage.setFont(lblImage.getFont().deriveFont(14f));
        }

        lblTitle = new JLabel("Title: " + product.getName());
        lblTitle.setFont(lblTitle.getFont().deriveFont(14f));

        lblPrice = new JLabel("Price: " + product.getPrice());
        lblPrice.setFont(lblPrice.getFont().deriveFont(14f));

        lblStock = new JLabel("Stock: " + product.getStockQuantity());
        lblStock.setFont(lblStock.getFont().deriveFont(14f));

        lblCategory = new JLabel("Category: " + product.getCategory());
        lblCategory.setFont(lblCategory.getFont().deriveFont(14f));

        lblDescription = new JLabel("Description: " + product.getDescription());
        lblDescription.setFont(lblDescription.getFont().deriveFont(14f));

        btnAddToCart = new JButton("Add To Cart");
        btnAddToCart.setBackground(new Color(190, 240, 240));
        btnAddToCart.setFont(btnAddToCart.getFont().deriveFont(14f));
    }

    private void buildUI() {

        add(lblImage, BorderLayout.WEST);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(5, 1));
        infoPanel.setBackground(new Color(240, 240, 240));

        infoPanel.add(lblTitle);
        infoPanel.add(lblPrice);
        infoPanel.add(lblStock);
        infoPanel.add(lblCategory);
        infoPanel.add(lblDescription);

        add(infoPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(new Color(240, 240, 240));

        bottomPanel.add(btnAddToCart);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public JButton getBtnAddToCart() {
        return btnAddToCart;
    }

    public Product getProduct() {
        return product;
    }
}
