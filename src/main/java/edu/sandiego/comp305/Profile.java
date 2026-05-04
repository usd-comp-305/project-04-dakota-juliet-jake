package edu.sandiego.comp305;

public abstract class Profile {

    private String name;

    private String username;

    private String password;

    private double rating;

    private double ratingTotal;

    private int ratingCount;

    protected Profile(){
        this.ratingTotal = 0;
        this.ratingCount = 0;
    }

    public Profile(final String name, final String username, final String password){
        setName(name);
        setUsername(username);
        setPassword(password);

        this.ratingTotal = 0;
        this.ratingCount = 0;
    }

    public void setName(final String name){

        if (name.isEmpty()) {
            throw new IllegalArgumentException("No name entered");
        }
        this.name = name;
    }

    public void setUsername(final String username){

        if (username.isEmpty()){
            throw new IllegalArgumentException("No username entered");
        }
        this.username = username;
    }

    public void setPassword(final String password){

        //password must contain 1 uppercase, lowercase,
        //number, special character
        if (validPassword(password)){
            this.password = password;
        }
    }

    private boolean validPassword(final String password){

        if (password.isEmpty()){
            throw new IllegalArgumentException("No password entered");
        } else if (!password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Password must contain " +
                    "1 uppercase");
        } else if (!password.matches(".*[a-z].*")) {
            throw new IllegalArgumentException("Password must contain " +
                    "1 lowercase");
        } else if (!password.matches(".*[0-9].*")) {
            throw new IllegalArgumentException("Password must contain " +
                    "1 number");
        } else if (!password.matches(".*[!@#$%^&*].*")) {
            throw new IllegalArgumentException("Password must contain " +
                    "1 special character");
        }

        return true;
    }

    public void adjustRating(final double newRating){

        if (newRating < 0.0 || newRating > 5.0){
            throw new IllegalArgumentException("Rating must be " +
                    "between 0 and 5");
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

    public int getRatingCount() {
        return this.ratingCount;
    }

    public void cancelCall(){}

}
