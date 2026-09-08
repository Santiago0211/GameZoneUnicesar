package com.gamezone.model;

//Leader

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Sale implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private LocalDate date;
    private Customer customer;
    private Seller seller;
    private List<Product> products;

    public Sale(String id, LocalDate date, Customer customer, Seller seller, List<Product> products) {
        this.id = id;
        this.date = date;
        this.customer = customer;
        this.seller = seller;
        this.products = products != null ? products : new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    
    public double calculateTotal() {
        double total = 0.0;
        for (Product p : products) {
            total += p.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sale ID: ").append(id)
                .append(" | Date: ").append(date)
                .append(" | Customer: ").append(customer.getName())
                .append(" | Seller: ").append(seller.getName())
                .append(" | Products: ");
        for (Product p : products) {
            sb.append(p.getTitle()).append(", ");
        }
        sb.append("| Total: $").append(calculateTotal());
        return sb.toString();
    }
}
