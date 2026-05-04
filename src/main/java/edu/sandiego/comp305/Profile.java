package edu.sandiego.comp305;

public abstract class Profile {

    private String name;

    private String username;

    private String password;

    private double rating;

    private double ratingTotal;

    private int ratingCount;

    Profile(){
        this.ratingTotal = 0;
        this.ratingCount = 0;
    }

    public void setName(String name){
        //check if given name is empty
        if (name.isEmpty()) throw new IllegalArgumentException("No name entered");
        this.name = name;
    }

    public void setUsername(String username){
        //check if given username is empty
        if (username.isEmpty()) throw new IllegalArgumentException("No username entered");
        this.username = username;
    }

    public void setPassword(String password){

        //check if given password is empty
        if (password.isEmpty()) throw new IllegalArgumentException("No password entered");

        //password must contain 1 uppercase, 1 lowercase, 1 number, 1 special character
        if (!password.matches(".*[A-Z].*")){
            throw new IllegalArgumentException("Password must contain 1 uppercase");
        }
        else if (!password.matches(".*[a-z].*")){
            throw new IllegalArgumentException("Password must contain 1 lowercase");
        }
        else if (!password.matches(".*[0-9].*")){
            throw new IllegalArgumentException("Password must contain 1 number");
        }
        else if (!password.matches(".*[!@#$%^&*].*")){
            throw new IllegalArgumentException("Password must contain 1 special character");
        }
        else this.password = password;
    }

    public void adjustRating(double newRating){
        //check if legal rating
        if (newRating < 0.0 || newRating > 5.0){
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }

        this.ratingTotal += newRating;
        this.ratingCount++;
        this.rating = this.ratingTotal / this.ratingCount;
    }

    public String getName(){
        return this.name;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public double getRating(){
        return this.rating;
    }

    public void cancelCall(){}
}
