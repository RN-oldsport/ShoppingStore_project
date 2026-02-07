package controller;

import common.OperationResult;
import model.Customer;
import model.Order;
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

        connectViewCartButton();
        connectPurchaseButton();

        refreshCustomerPanel();
    }



    private void refreshCustomerPanel() {
        loadProductsToPanel();
        connectProductCards();
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

                JOptionPane.showMessageDialog(mainFrame,
                        "Added to cart: " + product.getName());

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

            OperationResult<Order> result = orderService.placeOrder(customer.getId());

            if (result.isSuccess()) {

                JOptionPane.showMessageDialog(mainFrame,
                        "Purchase successful! Order ID: " + result.getData().getId());

                // Reload customer from json (updated balance + cleared cart)
                Customer updatedCustomer = customerService.getCustomerById(customer.getId());

                if (updatedCustomer != null) {
                    mainFrame.setCurrentUser(updatedCustomer);
                }

                // Refresh products list (stock updated)
                refreshCustomerPanel();

            } else {
                JOptionPane.showMessageDialog(mainFrame,
                        "Purchase failed: " + result.getMessage());
            }

        });
    }
}
