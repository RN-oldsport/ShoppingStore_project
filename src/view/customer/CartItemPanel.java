package view.customer;

import model.CartItem;

import javax.swing.*;
import java.awt.*;

public class CartItemPanel extends JPanel {

    private CartItem item;

    private JLabel lblProductId;
    private JLabel lblQuantity;

    private JButton btnIncrease;
    private JButton btnDecrease;
    private JButton btnRemove;

    public CartItemPanel(CartItem item) {
        this.item = item;

        setLayout(new FlowLayout(FlowLayout.CENTER, 25, 5));
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        initComponents();
        buildUI();
    }

    private void initComponents() {
        lblProductId = new JLabel("Product ID: " + item.getProductId());
        lblQuantity = new JLabel("Qty: " + item.getQuantity());

        btnIncrease = new JButton("+");
        btnDecrease = new JButton("-");
        btnRemove = new JButton("Remove");

        btnIncrease.setBackground(new Color(190, 240, 240));
        btnDecrease.setBackground(new Color(190, 240, 240));
        btnRemove.setBackground(new Color(240, 170, 190));
    }

    private void buildUI() {
        add(lblProductId);
        add(lblQuantity);
        add(btnIncrease);
        add(btnDecrease);
        add(btnRemove);
    }

    public CartItem getItem() {
        return item;
    }

    public void refreshQuantity() {
        lblQuantity.setText("Qty: " + item.getQuantity());
    }

    public JButton getBtnIncrease() {
        return btnIncrease;
    }

    public JButton getBtnDecrease() {
        return btnDecrease;
    }

    public JButton getBtnRemove() {
        return btnRemove;
    }
}
