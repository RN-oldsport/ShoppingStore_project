package services;

import model.Cart;
import model.CartItem;
import model.Customer;
import model.Product;

import java.util.List;

public class CartServices {

    private final ProductServices productServices;

    public CartServices(ProductServices productServices) {
        this.productServices = productServices;
    }

    // ==========================
    // Add Product to Cart
    // ==========================
    public void addToCart(Customer customer, Product product, int quantity) {
        if (customer.getCart() == null) {
            customer.setCart(new Cart());
        }

        Cart cart = customer.getCart();
        List<CartItem> items = cart.getItems();

        // Check if item already exists
        for (CartItem item : items) {
            if (item.getProductId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        // Add new item
        items.add(new CartItem(product.getId(), quantity));
    }

    // ==========================
    // Remove Item
    // ==========================
    public void removeFromCart(Customer customer, int productId) {
        Cart cart = customer.getCart();
        if (cart == null) return;
        cart.getItems().removeIf(item -> item.getProductId() == productId);
    }

    // ==========================
    // Increase Quantity
    // ==========================
    public void increaseQuantity(Customer customer, int productId) {
        Cart cart = customer.getCart();
        if (cart == null) return;

        for (CartItem item : cart.getItems()) {
            if (item.getProductId() == productId) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
    }

    // ==========================
    // Decrease Quantity
    // ==========================
    public void decreaseQuantity(Customer customer, int productId) {
        Cart cart = customer.getCart();
        if (cart == null) return;

        for (CartItem item : cart.getItems()) {
            if (item.getProductId() == productId) {
                if (item.getQuantity() > 1)
                    item.setQuantity(item.getQuantity() - 1);
                else
                    removeFromCart(customer, productId);
                return;
            }
        }
    }

    // ==========================
    // Get Cart Items
    // ==========================
    public List<CartItem> getCartItems(Customer customer) {
        if (customer.getCart() == null) {
            customer.setCart(new Cart());
        }
        return customer.getCart().getItems();
    }

    // ==========================
    // Calculate Total Price
    // ==========================
    public double calculateCartTotal(Customer customer) {
        double total = 0.0;
        if (customer.getCart() == null) return total;

        for (CartItem item : customer.getCart().getItems()) {
            Product product = productServices.findProductById(item.getProductId());
            if (product != null) {
                total += product.getPrice() * item.getQuantity();
            }
        }
        return total;
    }

    // ==========================
    // Clear Cart
    // ==========================
    public void clearCart(Customer customer) {
        if (customer.getCart() != null) {
            customer.getCart().getItems().clear();
        }
    }
}
