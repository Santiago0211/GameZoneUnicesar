package com.gamezone.model;

import java.io.Serializable;

/*Product is the base class for anything the store sells. It is
 abstract because "a generic product" with no specific type
 (video game or console) is not a real thing the store handles, so
 t should never be instantiated on its own.*/

//Desarrollador 1.

public abstract class Product implements Serializable{
    
    private static final long serialVersionUID = 1L; 
    
    private String id; 
    private String title; 
    private double price; 
    private int stock; 
    
    public Product(String id, String title, double price, int stock){
        this.id = id; 
        this.title = title; 
        this.price = price; 
        this.stock = stock; 
    }
    
    public String getId(){
        return id; 
    }
    
    public void setId(String id){
        this.id = id; 
    }
    
    public String getTitle(){
        return title; 
    }
    
    public void setTitle(String title){
        this.title = title; 
    }
    
    public double getPrice(){
        return price; 
    }
    
    public void setPrice(double price){
        this.price = price; 
    }
    
    public int getStock(){
        return stock; 
    }
    
    public void setStock(int stock){
        this.stock = stock; 
    }
    
/*
 Every product type must be able to build its own full
 description, mixing the common attributes (title, price...)
 with the attributes that only that type has (platform, brand...).
 declaring it abstract here forces every subclass to implement it.
 
 @return a human readable description of the product
 */
    public abstract String getDescription(); 
}