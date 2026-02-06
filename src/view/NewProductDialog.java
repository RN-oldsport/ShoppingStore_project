package view;

import model.Product;

import javax.swing.*;
import java.awt.*;

public class NewProductDialog extends JDialog {

    private JTextField txtName;
    private JTextField txtCategory;
    private JTextField txtPrice;
    private JTextField txtStock;
    private JTextField txtDescription;
    private JTextField txtImage;

    private JButton btnAdd;

    public NewProductDialog(JFrame parent) {

        super(parent, "New Item", true);

        setSize(600, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        initComponents();
        buildUI();
    }

    private void initComponents() {

        txtName = new JTextField();
        txtCategory = new JTextField();
        txtPrice = new JTextField();
        txtStock = new JTextField();
        txtDescription = new JTextField();
        txtImage = new JTextField();

        btnAdd = new JButton("Add Item");
    }

    private void buildUI() {

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(6, 2, 10, 10));
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        fieldsPanel.add(new JLabel("Name:"));
        fieldsPanel.add(txtName);

        fieldsPanel.add(new JLabel("Category:"));
        fieldsPanel.add(txtCategory);

        fieldsPanel.add(new JLabel("Price:"));
        fieldsPanel.add(txtPrice);

        fieldsPanel.add(new JLabel("Stock:"));
        fieldsPanel.add(txtStock);

        fieldsPanel.add(new JLabel("Description:"));
        fieldsPanel.add(txtDescription);

        fieldsPanel.add(new JLabel("Image Path:"));
        fieldsPanel.add(txtImage);

        add(fieldsPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnAdd);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    // ------------------ Getters for Controller ------------------

    public JButton getBtnAdd() {
        return btnAdd;
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

}
