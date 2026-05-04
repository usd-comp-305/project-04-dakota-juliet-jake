package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;

public class Listing {
    List<ServicerAccount> servicerObservers;

    private String providerName;

    private ArrayList<Service> servicesOffered;

    private String availability;

    private String generalServiceType;

    public void registerObserver(final ServicerAccount servicer){
        if (!servicerObservers.contains(servicer)) {
            servicerObservers.add(servicer);
        }
    public Listing(final String providerName,
                   final ArrayList<Service> services,
                   final String availability) {
        this.providerName = providerName;
        this.servicesOffered = new ArrayList<>(services);
        this.availability = availability;
    }

    public void removeObserver(final ServicerAccount servicer){
        servicerObservers.remove(servicer);
    }

    public void notifyObservers(final String customerName,
                                final String address,
                                final Service service){
        for (ServicerAccount servicer : servicerObservers) {
            servicer.update(customerName, address, service);
        }
    }

    public void getSelectedBy(final Customer customer) {}

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
