package view.admin;

import model.Product;

import javax.swing.*;
import java.awt.*;

public class ModifyProductDialog extends JDialog {

    private Product product;

    private JTextField txtName;
    private JTextField txtCategory;
    private JTextField txtPrice;
    private JTextField txtStock;
    private JTextField txtDescription;
    private JTextField txtImage;

    private JButton btnUpdateName;
    private JButton btnUpdateCategory;
    private JButton btnUpdatePrice;
    private JButton btnUpdateStock;
    private JButton btnUpdateDescription;
    private JButton btnUpdateImage;

    private JButton btnClose;

    public ModifyProductDialog(JFrame parent, Product product) {

        super(parent, "Modify Product (ID: " + product.getId() + ")", true);

        this.product = product;

        setSize(600, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        initComponents();
        buildUI();
    }

    private void initComponents() {

        txtName = new JTextField(product.getName());
        txtCategory = new JTextField(product.getCategory());
        txtPrice = new JTextField(String.valueOf(product.getPrice()));
        txtStock = new JTextField(String.valueOf(product.getStockQuantity()));
        txtDescription = new JTextField(product.getDescription());
        txtImage = new JTextField(product.getImage());

        btnUpdateName = new JButton("Update Name");
        btnUpdateCategory = new JButton("Update Category");
        btnUpdatePrice = new JButton("Update Price");
        btnUpdateStock = new JButton("Update Stock");
        btnUpdateDescription = new JButton("Update Description");
        btnUpdateImage = new JButton("Update Image");

        btnClose = new JButton("Close");

        btnUpdateCategory.setBackground(new Color(190, 240, 240));
        btnUpdatePrice.setBackground(new Color(190, 240, 240));
        btnUpdateStock.setBackground(new Color(190, 240, 240));
        btnUpdateDescription.setBackground(new Color(190, 240, 240));
        btnUpdateImage.setBackground(new Color(190, 240, 240));
        btnUpdateName.setBackground(new Color(190, 240, 240));
        btnUpdateCategory.setBackground(new Color(190, 240, 240));
        btnUpdatePrice.setBackground(new Color(190, 240, 240));
        btnUpdateStock.setBackground(new Color(190, 240, 240));
        btnUpdateDescription.setBackground(new Color(190, 240, 240));
        btnUpdateImage.setBackground(new Color(190, 240, 240));

        btnClose.setBackground(new Color(240, 170, 190));
    }

    private void buildUI() {

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(6, 3, 10, 10));
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        fieldsPanel.add(new JLabel("Name:"));
        fieldsPanel.add(txtName);
        fieldsPanel.add(btnUpdateName);

        fieldsPanel.add(new JLabel("Category:"));
        fieldsPanel.add(txtCategory);
        fieldsPanel.add(btnUpdateCategory);

        fieldsPanel.add(new JLabel("Price:"));
        fieldsPanel.add(txtPrice);
        fieldsPanel.add(btnUpdatePrice);

        fieldsPanel.add(new JLabel("Stock:"));
        fieldsPanel.add(txtStock);
        fieldsPanel.add(btnUpdateStock);

        fieldsPanel.add(new JLabel("Description:"));
        fieldsPanel.add(txtDescription);
        fieldsPanel.add(btnUpdateDescription);

        fieldsPanel.add(new JLabel("Image Path:"));
        fieldsPanel.add(txtImage);
        fieldsPanel.add(btnUpdateImage);

        add(fieldsPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnClose);

        add(bottomPanel, BorderLayout.SOUTH);
    }



    public JButton getBtnUpdateName() {
        return btnUpdateName;
    }

    public JButton getBtnUpdateCategory() {
        return btnUpdateCategory;
    }

    public JButton getBtnUpdatePrice() {
        return btnUpdatePrice;
    }

    public JButton getBtnUpdateStock() {
        return btnUpdateStock;
    }

    public JButton getBtnUpdateDescription() {
        return btnUpdateDescription;
    }

    public JButton getBtnUpdateImage() {
        return btnUpdateImage;
    }

    public JButton getBtnClose() {
        return btnClose;
    }

    public JTextField getTxtName() {
        return txtName;
    }

    public JTextField getTxtCategory() {
        return txtCategory;
    }

    public JTextField getTxtPrice() {
        return txtPrice;
    }

    public JTextField getTxtStock() {
        return txtStock;
    }

    public JTextField getTxtDescription() {
        return txtDescription;
    }

    public JTextField getTxtImage() {
        return txtImage;
    }

    public Product getProduct() {
        return product;
    }
}
