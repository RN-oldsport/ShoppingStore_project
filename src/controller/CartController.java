package controller;

import model.CartItem;
import model.Customer;
import services.CartServices;
import view.customer.CartDialog;
import view.customer.CartItemPanel;

import javax.swing.*;
import java.util.List;

public class CartController {

    private final CartDialog cartDialog;
    private final CartServices cartService;
    private final Customer customer;

    public CartController(CartDialog cartDialog, CartServices cartService, Customer customer) {
        this.cartDialog = cartDialog;
        this.cartService = cartService;
        this.customer = customer;

        connectItemButtons();
    }

    private void connectItemButtons() {
        List<CartItemPanel> itemPanels = cartDialog.getItemPanels();

        for (CartItemPanel panel : itemPanels) {
            panel.getBtnIncrease().addActionListener(e -> {
                cartService.increaseQuantity(customer, panel.getItem().getProductId());
                panel.refreshQuantity();

                // Total price gets updated
                double totalCost = cartService.calculateCartTotal(customer);
                cartDialog.getLblTotalPrice().setText("Total Price: " + totalCost);
            });

            panel.getBtnDecrease().addActionListener(e -> {
                cartService.decreaseQuantity(customer, panel.getItem().getProductId());
                panel.refreshQuantity();

                // Total price gets updated
                double totalCost = cartService.calculateCartTotal(customer);
                cartDialog.getLblTotalPrice().setText("Total Price: " + totalCost);
            });

            panel.getBtnRemove().addActionListener(e -> {
                cartService.removeFromCart(customer, panel.getItem().getProductId());
                cartDialog.refreshItems(customer.getCart().getItems());

                // Total price gets updated
                double totalCost = cartService.calculateCartTotal(customer);
                cartDialog.getLblTotalPrice().setText("Total Price: " + totalCost);
            });
        }
    }
}
