package edu.sandiego.comp305;

public abstract class Profile {
    private String name;

    private String username;

    private String password;

    private double ratingTotal;

    private int ratingCount;

    Profile(final String name, final String username, final String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.ratingTotal = 5.0;
        this.ratingCount = 0;
    }

    public void cancelCall(){}

    protected String getName() {
        return name;
    }

    protected String getUsername() {
        return username;
    }

    protected String getPassword() {
        return password;
    }

    protected double getRatingTotal() {
        return ratingTotal;
    }

    protected double getRatingCount() {
        return ratingCount;
    }
}
