package view.customer;

import model.CartItem;
import model.Customer;
import services.CartServices;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CartDialog extends JDialog {

    private final Customer customer;
    private final CartServices cartService;

    private JPanel itemsContainer;
    private List<CartItemPanel> itemPanels;

    public CartDialog(JFrame parent, CartServices cartService, Customer customer) {
        super(parent, "Your Cart", true);
        this.customer = customer;
        this.cartService = cartService;

        itemPanels = new ArrayList<>();
        itemsContainer = new JPanel();
        itemsContainer.setLayout(new BoxLayout(itemsContainer, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(itemsContainer);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        add(scrollPane, BorderLayout.CENTER);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(e -> setVisible(false));
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(btnClose);
        add(bottomPanel, BorderLayout.SOUTH);

        refreshItems(cartService.getCartItems(customer));
        pack();
        setLocationRelativeTo(parent);
    }


    // Refresh Items in Dialog
    public void refreshItems(List<CartItem> items) {
        itemsContainer.removeAll();
        itemPanels.clear();

        for (CartItem item : items) {
            CartItemPanel panel = new CartItemPanel(item);
            itemPanels.add(panel);
            itemsContainer.add(panel);
            itemsContainer.add(Box.createRigidArea(new Dimension(1, 10)));
        }

        itemsContainer.revalidate();
        itemsContainer.repaint();
    }


    // Getter for CartItemPanels (for controller)
    public List<CartItemPanel> getItemPanels() {
        return itemPanels;
    }
}
