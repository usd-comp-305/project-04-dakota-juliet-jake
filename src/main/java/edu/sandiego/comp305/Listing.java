package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;

public class Listing {
    private String providerName;

    private ArrayList<Service> servicesOffered;

    private String availability;

    List<ServicerAccount> servicerObservers;

    public Listing(final String providerName,
                   final ArrayList<Service> services,
                   final String availability) {
        this.providerName = providerName;
        this.servicesOffered = services;
        this.availability = availability;
    }

    public void registerObserver(final ServicerAccount servicer){
        if (!servicerObservers.contains(servicer)) {
            servicerObservers.add(servicer);
        }
    }

    public void removeObserver(final ServicerAccount servicer){
        servicerObservers.remove(servicer);
    }

    public void notifyObservers(final String customerName, final String address,
                                final Service service){
        for (ServicerAccount servicer : servicerObservers) {
            servicer.update(customerName, address, service);
        }
    }

    public void getSelectedBy(Customer customer) {}

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

    public String getServiceName() { return "";}

    public String getAvailability() {
        return availability;
    }
}
