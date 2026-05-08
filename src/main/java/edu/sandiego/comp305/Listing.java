package edu.sandiego.comp305;


import java.util.ArrayList;

public class Listing {
    ServicerAccount servicer;

    private String providerName;

    private ArrayList<Service> servicesOffered;

    private String availability;

    private boolean isAvailable;

    private String generalServiceType;

    public Listing(final String providerName,
                   final String availability,
                   final String generalServiceType,
                   final ArrayList<Service> servicesOffered,
                   final ServicerAccount servicer) {
        this.providerName = providerName;
        this.availability = availability;
        this.generalServiceType = generalServiceType;
        this.servicesOffered = new ArrayList<>(servicesOffered);
        this.servicer = servicer;
        this.isAvailable = true;
    }

    public void selectedByCustomer(final Customer customer,
                                   final Service selectedService) {
        this.isAvailable = false;
        servicer.update(customer, selectedService);
    }

    public void setProviderName(final String name){
        this.providerName = name;
    }

    public void setServicesOffered(final ArrayList<Service> services){
        this.servicesOffered = new ArrayList<>(services);
    }

    public void setAvailability(final String availability){
        this.availability = availability;
    }

    public String getProviderName(){
        return this.providerName;
    }

    public ArrayList<Service> getServicesOffered() {
        return new ArrayList<>(this.servicesOffered);
    }

    public String getGeneralServiceType() {
        return this.generalServiceType;
    }

    public String getAvailability() {
        return this.availability;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }
}
