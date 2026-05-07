package edu.sandiego.comp305;


import java.util.ArrayList;
import java.util.List;

public class Listing {
    ServicerAccount servicer;

    private String providerName;

    private ArrayList<Service> servicesOffered;

    private String availability;

    private String generalServiceType;

    public Listing(final String providerName,
                   final String availability,
                   final String generalServiceType,
                   final ArrayList<Service> servicesOffered,
                   ServicerAccount servicer) {
        this.providerName = providerName;
        this.availability = availability;
        this.generalServiceType = generalServiceType;
        this.servicesOffered = servicesOffered;
        this.servicer = servicer;
    }

    public void notifyServicer(final Customer customer,
                                final Service service){
        servicer.update(customer, service);
    }

    public void getSelectedBy(final Customer customer, final Service selectedService) {

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

    public ArrayList<Service> getServicesOffered() {
        return new ArrayList<>(servicesOffered);
    }

    public String getGeneralServiceType() {
        return generalServiceType;
    }

    public String getAvailability() {
        return availability;
    }

    public boolean isAvailable() {
        return true;
    }
}
