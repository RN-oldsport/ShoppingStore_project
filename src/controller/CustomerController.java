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
        loadProductsToPanel(); // Updates products and cards + clears all old things inside container
        connectProductCards(); // Connects listeners to cards again
    }


    private void loadProductsToPanel() {
        List<Product> products = productService.getProducts(); // Loads new product list from repo
        customerPanel.refreshProducts(products); // Loads new cards to customer panel, with passing new products list to make cards
    }


    private void connectProductCards() {

        List<CustomerProductCardPanel> cards = customerPanel.getCards();

        for (CustomerProductCardPanel card : cards) {

            card.getBtnAddToCart().addActionListener(e -> {

                Customer customer = (Customer) mainFrame.getCurrentUser();
                Product product = card.getProduct();

                // Adds and Saves into Users repo
                cartService.addToCart(customer, product, 1);

                // Set this customer to main frame
                mainFrame.setCurrentUser(customer);

                JOptionPane.showMessageDialog(mainFrame,
                        "Added to cart: " + product.getName());

            });
        }
    }


    private void connectViewCartButton() {

        customerPanel.getBtnViewCart().addActionListener(e -> {

            Customer customer = (Customer) mainFrame.getCurrentUser();

            CartDialog cartDialog = new CartDialog(mainFrame, cartService, customer, productService);

            new CartController(cartDialog, cartService, customer);

            // Total price gets updated
            double totalCost = cartService.calculateCartTotal(customer);
            cartDialog.getLblTotalPrice().setText("Total Price: " + totalCost);

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

                // Refresh Balance
                customerPanel.getLblBalance().setText("Balance: " + updatedCustomer.getBalance());

            } else {
                JOptionPane.showMessageDialog(mainFrame,
                        "Purchase failed: " + result.getMessage());
            }

        });
    }
}
