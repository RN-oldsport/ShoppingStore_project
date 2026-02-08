package view.admin;

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
        txtName.setFont(txtName.getFont().deriveFont(14f));

        txtCategory = new JTextField();
        txtCategory.setFont(txtCategory.getFont().deriveFont(14f));

        txtPrice = new JTextField();
        txtPrice.setFont(txtPrice.getFont().deriveFont(14f));

        txtStock = new JTextField();
        txtStock.setFont(txtStock.getFont().deriveFont(14f));

        txtDescription = new JTextField();
        txtDescription.setFont(txtDescription.getFont().deriveFont(14f));

        txtImage = new JTextField();
        txtImage.setFont(txtImage.getFont().deriveFont(14f));

        btnAdd = new JButton("Add Item");
        btnAdd.setFont(btnAdd.getFont().deriveFont(14f));
        btnAdd.setBackground(new Color(190, 240, 240));
    }

    private void buildUI() {

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(6, 2, 10, 10));
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblName = new JLabel("Name:");
        lblName.setFont(lblName.getFont().deriveFont(14f));
        fieldsPanel.add(lblName);
        fieldsPanel.add(txtName);

        JLabel lblCategory = new JLabel("Category:");
        lblCategory.setFont(lblCategory.getFont().deriveFont(14f));
        fieldsPanel.add(lblCategory);
        fieldsPanel.add(txtCategory);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setFont(lblPrice.getFont().deriveFont(14f));
        fieldsPanel.add(lblPrice);
        fieldsPanel.add(txtPrice);

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setFont(lblStock.getFont().deriveFont(14f));
        fieldsPanel.add(lblStock);
        fieldsPanel.add(txtStock);

        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setFont(lblDescription.getFont().deriveFont(14f));
        fieldsPanel.add(lblDescription);
        fieldsPanel.add(txtDescription);

        JLabel lblImage = new JLabel("Image Path:");
        lblImage.setFont(lblImage.getFont().deriveFont(14f));
        fieldsPanel.add(lblImage);
        fieldsPanel.add(txtImage);

        add(fieldsPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnAdd);

        add(bottomPanel, BorderLayout.SOUTH);
    }

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
