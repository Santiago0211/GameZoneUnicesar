package com.gamezone.model;

/*console is a product characterized by its brand, model and
generation (example: Sony, Play station 5, 9th generation).
*/

//DESARROLLADOR 1

public class Console extends Product{
    
    private static final long serialVersionUID = 1L; 
    
    private String brand; 
    private String model; 
    private String generation; 
    
    public Console(String id, String title, double price, int stock,
                    String brand, String model, String generation){
        super(id, title, price, stock); 
        this.brand = brand; 
        this.model = model; 
        this.generation = generation; 
    }
    
    public String getBrand(){
        return brand;
    }
    
    public void setBrand(String brand){
        this.brand = brand; 
    }
    
    public String getModel(){
        return model; 
    }
    
    public void setModel(String model){
        this.model = model; 
    }
    
    public String getGeneration(){
        return generation;
    }
    
    public void setGeneration(String generation){
        this.generation = generation; 
    }
    
    @Override
    public String getDescription(){
         return "[Console] " + getTitle()
                + " | Brand: " + brand
                + " | Model: " + model
                + " | Generation: " + generation
                + " | Price: $" + getPrice()
                + " | Stock: " + getStock();
    
}
