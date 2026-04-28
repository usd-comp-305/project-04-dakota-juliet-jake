package edu.sandiego.comp305;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Listing {
    private String providerName;
    private ArrayList<Service> servicesOffered;
    private String availability;

    public Listing(String providerName, ArrayList<Service> services, String availability) {
        this.providerName = providerName;
        this.servicesOffered = services;
        this.availability = availability;
    }

    public void setProviderName(String name){

    }

    public void setServicesOffered(ArrayList<Service> services){

    }

    public void setAvailability(String availability){

    }

    public String getProviderName(){
        return providerName;
    }

    public ArrayList<Service> getServicesOffered(){
        return servicesOffered;
    }

    public String getAvailability() {
        return availability;
    }
}
