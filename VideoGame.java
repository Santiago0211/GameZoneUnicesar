package com.gamezone.model; 

/*
 VideoGame is a Product that is developed for a specific platform,
 belongs to a genre, and has a recommended age rating.
 */

//Desarrollador 1

public class VideoGame extends Product {
    
    private static final long serialVersionUID = 1L; 
    
    private String platform; 
    private String genre; 
    private String ageRating; 
    
    public VideoGame(String id, String title, double price, int stock, String platform, String genre, String ageRating){
        
        super(id, title, price, stock); 
        this.platform = platform; 
        this.genre = genre; 
        this.ageRating = ageRating; 
    }
    
    public String getPlatform(){
        return platform; 
    }
    
    public void setPlatform(String platform){
        this.platform = platform; 
    }
    
    public String getGenre(){
        return genre; 
    }
    
    public void setGenre(String genre){
        this.genre = genre;
    }
    
    public String getAgeRating(){
        return ageRating; 
    }
    
    public void setAgeRating(String ageRating){
        this.ageRating = ageRating; 
    }
    
    @Override
    Public String getDescription(){
        return "[Video Game] " + getTitle()
                + " | Platform: " + platform
                + " | Genre: " + genre
                + " | Age Rating: " + ageRating
                + " | Price: $" + getPrice()
                + " | Stock: " + getStock();
    }
}
