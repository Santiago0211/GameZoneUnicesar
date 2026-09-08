package com.gamezone.persistence;

import com.gamezone.model.Product; 

import java.io.*; 
import java.util.ArrayList; 
import java.util.List; 

/*
 productRepository is the only class allowed to touch files for the
 products module. It knows nothing about business rules (that is the
 job of ProductService); it only knows how to save and load a list
 of Product objects using Java serialization.
 */

//DESARROLLADOR 1

public class ProductRepository{
    
    private final String filePath; 
    
    public ProductRepository(String filePath){
        this.filePath = filePath; 
    }
    
     /*
     Reads every product previously saved to disk.
     
     @return the list of products, or an empty list if the file does not exist yet
     */
    
    @SuppressWarnings("unchecked")
    public List<Product> loadAll(){
        File file = new File(filePath);
            if(!file.exists()){
            return new ArrayList<>();
        }
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(File))){
            return (List<Product>) ois.readObject(); 
        } catch (IOException | ClassNotFoundException e){
            System.out.println("ERROR LOADING PRODUCTS: " + e.getMessage());
            return new ArrayList<>(); 
        }
    }
    /*
     Overwrites the file with the current list of products.
     
     @param products the full, up to date list of products
     */
    
    public void saveAll(Lis<Product> products){
        File file = new File(filePath); 
        File parent = file.getParentFile(); 
        if(parent != null){
            parent.mkdirs(); 
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
           oos.writeObject(products);
        }catch (IOException e){
            System.out.println("ERROR SAVING PRODUCTS: " + e.getMessage());
        }
    }
}