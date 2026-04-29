package edu.sandiego.comp305;

import java.util.ArrayList;

public class Listing {
    private String providerName;

    private ArrayList<Service> servicesOffered;

    private String availability;

    public Listing(final String providerName,
                   final ArrayList<Service> services,
                   final String availability) {
        this.providerName = providerName;
        this.servicesOffered = services;
        this.availability = availability;
    }

    public void setProviderName(final String name){

    }

    public void setServicesOffered(final ArrayList<Service> services){

    }

    public void setAvailability(final String availability){

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
