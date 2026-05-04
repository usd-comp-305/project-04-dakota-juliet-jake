package edu.sandiego.comp305;

public class Service {

    private String name;

    private double price;

    Service(){}

    public Service(final String name, final double price){
        this.name = name;
        this.price = price;
    }

    public void setName(final String name){
        this.name = name;
    }

    public void setPrice(final double price){
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

}
