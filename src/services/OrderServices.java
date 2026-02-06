package services;

import common.OperationResult;
import model.*;
import repository.IOrderRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderServices {

    private final IOrderRepository orderRepo;
    private final ProductServices productServices;
    private final CustomerServices customerService;

    public OrderServices(IOrderRepository orderRepo,
                         ProductServices productServices,
                         CustomerServices customerService) {

        this.orderRepo = orderRepo;
        this.productServices = productServices;
        this.customerService = customerService;
    }

    // ==========================
    // Get All Orders
    // ==========================
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    // ==========================
    // Get Orders of One Customer
    // ==========================
    public List<Order> getCustomerOrders(int customerId) {
        return orderRepo.findByCustomerId(customerId);
    }

    // ==========================
    // Generate New Order ID
    // ==========================
    private int generateNewOrderId() {

        List<Order> orders = orderRepo.findAll();

        int maxId = 0;
        for (Order order : orders) {
            if (order.getId() > maxId)
                maxId = order.getId();
        }

        return maxId + 1;
    }

    // ==========================
    // Place Order (Purchase)
    // ==========================
    public OperationResult<Order> placeOrder(Customer customer) {

        if (customer == null)
            return new OperationResult<>(null, false, "Customer is null!");

        Cart cart = customer.getCart();

        if (cart == null || cart.getItems() == null || cart.getItems().isEmpty())
            return new OperationResult<>(null, false, "Cart is empty!");

        List<CartItem> cartItems = cart.getItems();
        List<OrderItem> orderItems = new ArrayList<>();

        int totalPrice = 0;

        // ==========================
        // Validate Cart Items
        // ==========================
        for (CartItem cartItem : cartItems) {

            int productId;

            try {
                productId = cartItem.getProductId();
            } catch (NumberFormatException e) {
                return new OperationResult<>(null, false, "Invalid productId in cart!");
            }

            if (cartItem.getQuantity() <= 0)
                return new OperationResult<>(null, false, "Invalid quantity for product id: " + productId);

            Product product = productServices.findProductById(productId);

            if (product == null)
                return new OperationResult<>(null, false, "Product not found (ID: " + productId + ")");

            // ==========================
            // Stock Check (Business Logic)
            // ==========================
            if (product.getStockQuantity() < cartItem.getQuantity()) {
                return new OperationResult<>(
                        null,
                        false,
                        "Not enough stock for product: " + product.getName() +
                                " | Available: " + product.getStockQuantity()
                );
            }

            int itemTotal = product.getPrice() * cartItem.getQuantity();
            totalPrice += itemTotal;

            // Save priceAtPurchase
            OrderItem orderItem = new OrderItem(productId, cartItem.getQuantity(), product.getPrice());
            orderItems.add(orderItem);
        }

        // ==========================
        // Balance Check (Business Logic)
        // ==========================
        if (customer.getBalance() < totalPrice) {
            return new OperationResult<>(
                    null,
                    false,
                    "Not enough balance! Total Price: " + totalPrice + " | Your Balance: " + customer.getBalance()
            );
        }

        // ==========================
        // Reduce Product Stock
        // ==========================
        for (OrderItem item : orderItems) {

            Product product = productServices.findProductById(item.getProductId());

            int newStock = product.getStockQuantity() - item.getQuantity();

            productServices.modifyProductStockQuantity(product.getId(), newStock);
        }

        // ==========================
        // Reduce Customer Balance
        // ==========================
        customerService.decreaseBalance(customer.getId(), totalPrice);

        // ==========================
        // Create Order Object
        // ==========================
        int orderId = generateNewOrderId();

        Order order = new Order(
                orderId,
                customer.getId(),
                orderItems,
                totalPrice,
                LocalDateTime.now().toString()
        );

        // ==========================
        // Save Order in Orders.json
        // ==========================
        List<Order> orders = orderRepo.findAll();
        orders.add(order);
        orderRepo.saveAll(orders);

        // ==========================
        // Clear Cart
        // ==========================
        customer.getCart().getItems().clear();

        // Save customer changes in Users.json
        customerService.updateCustomer(customer);

        return new OperationResult<>(order, true, "Order placed successfully! Order ID: " + orderId);
    }
}
