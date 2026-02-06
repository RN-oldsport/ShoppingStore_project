package model;

public class CartItem {

    private int productId;
    private int quantity;

    public CartItem() {}

    public CartItem(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }


    public int getProductId() {
        return productId;
    }
    public int getQuantity() {
        return quantity;
    }


    public void setProductId(int productId) {
        this.productId = productId;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }
    public void decreaseQuantity(int amount) {
        this.quantity -= amount;
    }

}
