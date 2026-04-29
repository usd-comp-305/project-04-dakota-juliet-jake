package edu.sandiego.comp305;

import java.util.List;

public abstract class Customer extends Profile {
    private String address;
    private List<ServicerAccount> observers;
    private String selectedService;

    public void registerObserver(ServicerAccount servicer){};

    public void removeObserver(ServicerAccount servicer){};

    public void notifyObservers(){};

    void selectService(String service){};

    void pay(Payment payment){};

    List<Listing> searchByPrice(String service, double maxPrice){};

    void joinQueue(String service){};

    List<Listing> searchByProvider(String providerName, String service){};
}
