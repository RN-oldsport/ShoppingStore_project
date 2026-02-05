package model;

import java.math.BigDecimal;

public class Product {

    private int id;
    private String name;
    private String category;
    private int price;
    private int stockQuantity;
    private String description;
    private String image;

    public Product(){}

    public Product(int id, String name, String category, int price, int stockQuantity, String description, String image) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.description = description;
        this.image = image;
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


    public int getPrice(){
        return price;
    }
    public void setPrice(int price){
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


    public  String getImage(){
        return image;
    }
    public void setImage(String image){
        this.image = image;
    }
}
