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
        txtName.setFont(txtName.getFont().deriveFont(14f));

        txtCategory = new JTextField(product.getCategory());
        txtCategory.setFont(txtCategory.getFont().deriveFont(14f));

        txtPrice = new JTextField(String.valueOf(product.getPrice()));
        txtPrice.setFont(txtPrice.getFont().deriveFont(14f));

        txtStock = new JTextField(String.valueOf(product.getStockQuantity()));
        txtStock.setFont(txtStock.getFont().deriveFont(14f));

        txtDescription = new JTextField(product.getDescription());
        txtDescription.setFont(txtDescription.getFont().deriveFont(14f));

        txtImage = new JTextField(product.getImage());
        txtImage.setFont(txtImage.getFont().deriveFont(14f));

        btnUpdateName = new JButton("Update Name");
        btnUpdateName.setFont(btnUpdateName.getFont().deriveFont(14f));
        btnUpdateName.setBackground(new Color(190, 240, 240));

        btnUpdateCategory = new JButton("Update Category");
        btnUpdateCategory.setFont(btnUpdateCategory.getFont().deriveFont(14f));
        btnUpdateCategory.setBackground(new Color(190, 240, 240));

        btnUpdatePrice = new JButton("Update Price");
        btnUpdatePrice.setFont(btnUpdatePrice.getFont().deriveFont(14f));
        btnUpdatePrice.setBackground(new Color(190, 240, 240));

        btnUpdateStock = new JButton("Update Stock");
        btnUpdateStock.setFont(btnUpdateStock.getFont().deriveFont(14f));
        btnUpdateStock.setBackground(new Color(190, 240, 240));

        btnUpdateDescription = new JButton("Update Description");
        btnUpdateDescription.setFont(btnUpdateDescription.getFont().deriveFont(14f));
        btnUpdateDescription.setBackground(new Color(190, 240, 240));

        btnUpdateImage = new JButton("Update Image");
        btnUpdateImage.setFont(btnUpdateImage.getFont().deriveFont(14f));
        btnUpdateImage.setBackground(new Color(190, 240, 240));

        btnClose = new JButton("Close");
        btnClose.setFont(btnClose.getFont().deriveFont(14f));
        btnClose.setBackground(new Color(240, 170, 190));
    }

    private void buildUI() {

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(6, 3, 10, 10));
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblName = new JLabel("Name:");
        lblName.setFont(lblName.getFont().deriveFont(14f));
        fieldsPanel.add(lblName);
        fieldsPanel.add(txtName);
        fieldsPanel.add(btnUpdateName);

        JLabel lblCategory = new JLabel("Category:");
        lblCategory.setFont(lblCategory.getFont().deriveFont(14f));
        fieldsPanel.add(lblCategory);
        fieldsPanel.add(txtCategory);
        fieldsPanel.add(btnUpdateCategory);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setFont(lblPrice.getFont().deriveFont(14f));
        fieldsPanel.add(lblPrice);
        fieldsPanel.add(txtPrice);
        fieldsPanel.add(btnUpdatePrice);

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setFont(lblStock.getFont().deriveFont(14f));
        fieldsPanel.add(lblStock);
        fieldsPanel.add(txtStock);
        fieldsPanel.add(btnUpdateStock);

        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setFont(lblDescription.getFont().deriveFont(14f));
        fieldsPanel.add(lblDescription);
        fieldsPanel.add(txtDescription);
        fieldsPanel.add(btnUpdateDescription);

        JLabel lblImage = new JLabel("Image Path:");
        lblImage.setFont(lblImage.getFont().deriveFont(14f));
        fieldsPanel.add(lblImage);
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
