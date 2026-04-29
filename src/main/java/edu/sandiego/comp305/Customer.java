package edu.sandiego.comp305;

import java.util.List;
import java.util.ArrayList;

public abstract class Customer extends Profile {
    private String address;

    private List<ServicerAccount> observers;

    private String selectedService;

    public void registerObserver(final ServicerAccount servicer){}

    public void removeObserver(final ServicerAccount servicer){}

    public void notifyObservers(){}

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
