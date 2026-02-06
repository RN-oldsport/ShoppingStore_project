package controller;

import model.Customer;
import model.Product;
import services.CartServices;
import services.CustomerServices;
import services.OrderServices;
import services.ProductServices;
import view.MainFrame;
import view.customer.CartDialog;
import view.customer.CustomerProductCardPanel;
import view.customer.CustomerProductPanel;

import javax.swing.*;
import java.util.List;

public class CustomerController {

    private final MainFrame mainFrame;
    private final CustomerServices customerService;
    private final ProductServices productService;
    private final CartServices cartService;
    private final OrderServices orderService;
    private final CustomerProductPanel customerPanel;

    public CustomerController(MainFrame mainFrame,
                              CustomerServices customerService,
                              ProductServices productService,
                              CartServices cartService,
                              OrderServices orderService) {

        this.mainFrame = mainFrame;
        this.customerService = customerService;
        this.productService = productService;
        this.cartService = cartService;
        this.orderService = orderService;
        this.customerPanel = mainFrame.getCustomerProductPanel();

        loadProductsToPanel();

        connectProductCards();
        connectViewCartButton();
        connectPurchaseButton();
    }


    private void loadProductsToPanel() {
        List<Product> products = productService.getProducts();
        customerPanel.refreshProducts(products);

    }


    private void connectProductCards() {
        List<CustomerProductCardPanel> cards = customerPanel.getCards();

        for (CustomerProductCardPanel card : cards) {
            card.getBtnAddToCart().addActionListener(e -> {
                Customer customer = (Customer) mainFrame.getCurrentUser();
                Product product = card.getProduct();
                cartService.addToCart(customer, product, 1);
                JOptionPane.showMessageDialog(mainFrame, "Product added to cart: " + product.getName());
            });
        }
    }

    private void connectViewCartButton() {
        customerPanel.getBtnViewCart().addActionListener(e -> {
            Customer customer = (Customer) mainFrame.getCurrentUser();
            CartDialog cartDialog = new CartDialog(mainFrame, cartService, customer);

            new CartController(cartDialog, cartService, customer);

            cartDialog.setVisible(true);
        });
    }

    private void connectPurchaseButton() {
        customerPanel.getBtnPurchase().addActionListener(e -> {
            Customer customer = (Customer) mainFrame.getCurrentUser();
            double totalAmount = cartService.calculateCartTotal(customer);

            if (customer.getBalance() < totalAmount) {
                JOptionPane.showMessageDialog(mainFrame, "Insufficient balance! Please top up.");
                return;
            }

            // کم کردن موجودی مشتری
            var balanceResult = customerService.decreaseBalance(customer.getId(), totalAmount);
            if (!balanceResult.isSuccess()) {
                JOptionPane.showMessageDialog(mainFrame, "Error updating balance: " + balanceResult.getMessage());
                return;
            }

            // ثبت سفارش
            var orderResult = orderService.placeOrder(customer);
            if (orderResult.isSuccess()) {
                JOptionPane.showMessageDialog(mainFrame, "Purchase successful! Order ID: " + orderResult.getData().getId());
                cartService.clearCart(customer); // خالی کردن سبد خرید بعد از خرید موفق
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Purchase failed: " + orderResult.getMessage());
            }
        });
    }

}
