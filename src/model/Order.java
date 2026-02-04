package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private int id;
    private String customerUsername;
    private LocalDateTime timeOfPurchase;
    private List<OrderItem> orderItems;
    private BigDecimal cost;


    public Order(){
        this.orderItems = new ArrayList<>();
    }

    public Order(int id, String customerUsername,
                 LocalDateTime timeOfPurchase, List<OrderItem> orderItems, BigDecimal cost){
        this.id = id;
        this.customerUsername = customerUsername;
        this.timeOfPurchase = timeOfPurchase;
        this.orderItems = orderItems;
        this.cost = cost;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }


    public String getCustomerUsername(){
        return customerUsername;
    }
    public void setCustomerUsername(String customerUsername){
        this.customerUsername = customerUsername;
    }


    public LocalDateTime getTimeOfPurchase(){
        return timeOfPurchase;
    }
    public void setTimeOfPurchase(LocalDateTime timeOfPurchase){
        this.timeOfPurchase = timeOfPurchase;
    }


    public List<OrderItem> getOrderItems(){
        return orderItems;
    }
    public void setOrderItems(List<OrderItem> orderItems){
        this.orderItems = orderItems;
    }


    public BigDecimal getCost(){
        return cost;
    }
    public void setCost(BigDecimal cost){
        this.cost = cost;
    }

}
