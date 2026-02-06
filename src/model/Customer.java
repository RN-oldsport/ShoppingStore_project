package model;

import java.util.ArrayList;

public class Customer extends User {

    private Cart cart;
    private double balance;

    public Customer() {
        super();
        this.setRole(Role.CUSTOMER);
        this.cart = new Cart();
        this.balance = 0.0;
    }

    public Customer(String username, String password) {
        super(username, password, Role.CUSTOMER);
        this.cart = new Cart();
        this.balance = 0.0;
    }

    // =====================
    // Getters & Setters
    // =====================
    public Cart getCart() {
        if (cart == null)
            cart = new Cart();
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void increaseBalance(double amount) {
        if (amount > 0)
            this.balance += amount;
    }

    public void decreaseBalance(double amount) {
        if (amount > 0 && this.balance >= amount)
            this.balance -= amount;
    }
}
