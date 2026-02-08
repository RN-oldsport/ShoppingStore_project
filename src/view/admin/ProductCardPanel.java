package view.admin;

import model.Product;

import javax.swing.*;
import java.awt.*;

public class ProductCardPanel extends JPanel {

    private Product product;

    private JLabel lblImage;
    private JLabel lblTitle;
    private JLabel lblPrice;
    private JLabel lblStock;
    private JLabel lblCategory;
    private JLabel lblDescription;

    private JButton btnModify;
    private JButton btnDelete;

    public ProductCardPanel(Product product) {
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
        // بزرگ‌تر کردن لیبل تصویر و اسکیل تصویر برای هماهنگی با فونت 14 سایر اجزا
        lblImage.setPreferredSize(new Dimension(200, 200));
        lblImage.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);

        if (product.getImage() != null && !product.getImage().isEmpty()) {
            ImageIcon icon = new ImageIcon(product.getImage());
            Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
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

        btnModify = new JButton("Modify");
        btnModify.setFont(btnModify.getFont().deriveFont(14f));
        btnModify.setBackground(new Color(190, 240, 240));

        btnDelete = new JButton("Delete");
        btnDelete.setFont(btnDelete.getFont().deriveFont(14f));
        btnDelete.setBackground(new Color(240, 170, 190));
    }

    private void buildUI() {

        add(lblImage, BorderLayout.WEST);

        JPanel infoPanel = new JPanel(new GridLayout(5, 1));
        infoPanel.setBackground(new Color(240, 240, 240));

        infoPanel.add(lblTitle);
        infoPanel.add(lblPrice);
        infoPanel.add(lblStock);
        infoPanel.add(lblCategory);
        infoPanel.add(lblDescription);

        add(infoPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 5));
        buttonPanel.setBackground(new Color(240, 240, 240));

        buttonPanel.add(btnModify);
        buttonPanel.add(btnDelete);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JButton getBtnModify() {
        return btnModify;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public Product getProduct() {
        return product;
    }
}
