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

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public List<Order> getCustomerOrders(int customerId) {
        return orderRepo.findByCustomerId(customerId);
    }

    private int generateNewOrderId() {
        List<Order> orders = orderRepo.findAll();

        int maxId = 0;
        for (Order order : orders) {
            if (order.getId() > maxId)
                maxId = order.getId();
        }

        return maxId + 1;
    }

    public OperationResult<Order> placeOrder(int customerId) {

        // Load Fresh Customer
        Customer customer = customerService.getCustomerById(customerId);

        if (customer == null)
            return new OperationResult<>(null, false, "Customer not found!");

        Cart cart = customer.getCart();

        if (cart == null || cart.getItems() == null || cart.getItems().isEmpty())
            return new OperationResult<>(null, false, "Cart is empty!");

        List<CartItem> cartItems = cart.getItems();
        List<OrderItem> orderItems = new ArrayList<>();

        // Validate Items + Calculate Total, Create Order Item
        int totalPrice = 0;
        for (CartItem cartItem : cartItems) {

            int productId = cartItem.getProductId();

            if (cartItem.getQuantity() <= 0)
                return new OperationResult<>(null, false, "Invalid quantity for product id: " + productId);

            Product product = productServices.findProductById(productId);

            if (product == null)
                return new OperationResult<>(null, false, "Product not found (ID: " + productId + ")");

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

            OrderItem orderItem = new OrderItem(productId, cartItem.getQuantity(), product.getPrice());
            orderItems.add(orderItem);
        }


        // Check Balance
        if (customer.getBalance() < totalPrice) {
            return new OperationResult<>(
                    null,
                    false,
                    "Not enough balance! Total Price: " + totalPrice +
                            " | Your Balance: " + customer.getBalance()
            );
        }


        // Reduce Product Stock
        for (OrderItem item : orderItems) {

            Product product = productServices.findProductById(item.getProductId());

            int newStock = product.getStockQuantity() - item.getQuantity();

            OperationResult<Void> stockResult =
                    productServices.modifyProductStockQuantity(product.getId(), newStock);

            if (!stockResult.isSuccess()) {
                return new OperationResult<>(false,
                        "Failed to update stock for product: " + product.getName());
            }
        }


        // Reduce Customer Balance
        double newBalance = customer.getBalance() - totalPrice;
        customer.setBalance(newBalance);


        // Create Order
        int orderId = generateNewOrderId();

        Order order = new Order(
                orderId,
                customer.getId(),
                orderItems,
                totalPrice,
                LocalDateTime.now().toString()
        );


        // Save Order
        List<Order> orders = orderRepo.findAll();
        orders.add(order);
        orderRepo.saveAll(orders);


        // Clear Cart
        customer.getCart().getItems().clear();


        // Save Customer Changes (balance + cart) in User repo
        OperationResult<Void> updateResult = customerService.updateCustomer(customer);

        if (!updateResult.isSuccess()) {
            return new OperationResult<>(null, false,
                    "Order saved but customer update failed!");
        }

        return new OperationResult<>(order, true,
                "Order placed successfully! Order ID: " + orderId);
    }
}
