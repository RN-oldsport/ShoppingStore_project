package view.customer;

import model.CartItem;
import model.Customer;
import services.CartServices;
import services.ProductServices;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CartDialog extends JDialog {

    private final Customer customer;
    private final CartServices cartService;
    private final ProductServices productServices;

    private JPanel itemsContainer;
    private List<CartItemPanel> itemPanels;
    private JLabel lblTotalPrice;

    public CartDialog(JFrame parent, CartServices cartService, Customer customer, ProductServices productServices) {
        super(parent, "Your Cart", true);
        this.customer = customer;
        this.cartService = cartService;
        this.productServices = productServices;

        itemPanels = new ArrayList<>();
        itemsContainer = new JPanel();
        itemsContainer.setLayout(new BoxLayout(itemsContainer, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(itemsContainer);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        JButton btnClose = new JButton("Close");
        btnClose.setFont(btnClose.getFont().deriveFont(14f));
        btnClose.addActionListener(e -> setVisible(false));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnClose.setBackground(new Color(240, 170, 190));
        bottomPanel.add(btnClose);
        add(bottomPanel, BorderLayout.SOUTH);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblTotalPrice = new JLabel("Total Price: 0");
        lblTotalPrice.setFont(lblTotalPrice.getFont().deriveFont(14f));
        topPanel.add(lblTotalPrice);
        add(topPanel, BorderLayout.NORTH);

        refreshItems(cartService.getCartItems(customer));
        pack();
        setLocationRelativeTo(parent);
    }

    public void refreshItems(List<CartItem> items) {
        itemsContainer.removeAll();
        itemPanels.clear();

        for (CartItem item : items) {
            CartItemPanel panel = new CartItemPanel(item, productServices);
            itemPanels.add(panel);
            itemsContainer.add(panel);
            itemsContainer.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        itemsContainer.revalidate();
        itemsContainer.repaint();
    }

    public List<CartItemPanel> getItemPanels() {
        return itemPanels;
    }

    public JLabel getLblTotalPrice() {
        return lblTotalPrice;
    }
}
