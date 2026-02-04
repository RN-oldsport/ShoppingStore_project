package model;

import java.math.BigDecimal;

public class Product {

    private int id;
    private String name;
    private String category;
    private BigDecimal price;
    private int stockQuantity;
    private String description;

    public Product(){}

    public Product(int id, String name, String category, BigDecimal price, int stockQuantity,  String description){
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.description = description;
    }


    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }


    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name= name;
    }


    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category = category;
    }


    public BigDecimal getPrice(){
        return price;
    }
    public void setPrice(BigDecimal price){
        this.price = price;
    }


    public int getStockQuantity(){
        return stockQuantity;
    }
    public void setStockQuantity(int stockQuantity){
        this.stockQuantity = stockQuantity;
    }


    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }


}
