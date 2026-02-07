package model;

public class OrderItem {

    private int productId;
    private int quantity;
    private int priceAtPurchase;

    public OrderItem() {
    }

    public OrderItem(int productId, int quantity, int priceAtPurchase) {
        this.productId = productId;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
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


    public int getPriceAtPurchase() {
        return priceAtPurchase;
    }
    public void setPriceAtPurchase(int priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }
}
