package edu.sandiego.comp305;

import java.util.List;
import java.util.ArrayList;

public abstract class Customer extends Profile {
    String address;

    List<ServicerAccount> servicerObservers;

    String selectedService;

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

    void selectService(final String service){}

    void pay(final Payment payment){}

    List<Listing> searchByPrice(final String service,
                                final double maxPrice){
        return new ArrayList<>();
    }

    void joinQueue(final String service){}

    List<Listing> searchByProvider(final String providerName,
                                   final String service){
        return new ArrayList<>();
    }
}
