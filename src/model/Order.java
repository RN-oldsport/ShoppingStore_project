package model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private int id;
    private int customerId;
    private List<OrderItem> items;
    private int totalPrice;
    private String orderDate;

    public Order() {
        this.items = new ArrayList<>();
    }

    public Order(int id, int customerId, List<OrderItem> items, int totalPrice, String orderDate) {
        this.id = id;
        this.customerId = customerId;
        this.items = items;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }


    public List<OrderItem> getItems() {
        return items;
    }
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }


    public int getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }


    public String getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
