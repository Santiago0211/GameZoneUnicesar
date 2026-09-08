package com.gamezone.service; 

import com.gamezone.model.Console; 
import com.gamezone.model.Product; 
import com.gamezone.model.VideoGame; 
import com.gamezone.persistence.ProductRepository; 

import java.util.ArrayList; 
import java.util.List; 

/*
 ProductService contains the business rules for products: how a new
 product gets its ID, how stock is checked and updated, and how the
 list of products is exposed to the rest of the system. This class
 is the only one allowed to call ProductRepository.
 */

//DESARROLLADOR 1.

public class ProductService{
    private final ProductRepository repository; 
    private final List<Product> products; 
    private int nextId; 
    
    public ProductService(ProductRepository repository){
        this.repository = repository; 
        this.products = repository.loadAll(); 
        this.nextId = products.size() + 1; 
    }
    
    public VideoGame registerVideoGame(String title, double price, int stock, String platform, String genre, String ageRating){
        String id = "VG-" + nextId++; 
        VideoGame game = new VideoGame(id, title, price, stock, platform, genre, ageRating); 
        products.add(game); 
        repository.saveAll(products);
        return game; 
    }
    
    public Console registerConsole(String title, double price, int stock, String brand, String model, String generation){
        String id = "CN-" + nextId++;
        Console console = new Console(id, title, price, stock, brand, model, generation); 
        products.add(console);
        repository.saveAll(products);
        return console; 
    }
    
    public List<Product> listProducts(){
        return new ArrayList<>(products);
    }
    
    public Product findById(String id){
        for(Product p: products){
            if (p.getId().equalsIgnoreCase(id)){
                return p; 
            }
        }
        return null; 
    }
    
    
    /*
     * Checks the business rule "a product cannot be sold when the
     * available quantity is not enough".
     */
    
    public boolean hasEnoughStock(Product product, int quantity){
        return product.getStock() >= quantity;
    }
    
     /*
     * Discounts the sold quantity from the inventory and persists the
     * change immediately, so stock is never lost between executions.
     */
    
    public void reduceStock(Product product, int quantity){
        product.setStock(product.getStock() - quantity);
        repository.saveAll(products);
    }
}

