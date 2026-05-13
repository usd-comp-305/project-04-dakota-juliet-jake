package edu.sandiego.comp305;

public class Service {

    private final String name;

    private final double price;

    public Service(final String name, final double price){
        this.name = name;
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

}
