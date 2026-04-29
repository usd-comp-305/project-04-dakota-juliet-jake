package edu.sandiego.comp305;

public abstract class Customer extends Profile {
    private String address;
    private List<Servicer> observers;
    private String selectedService;

    public void registerObserver(Servicer servicer){};

    public void removeObserver(Servicer servicer){};

    public void notifyObservers(){};

    void selectService(String service){};

    void pay(Payment payment){};

    List<Listing> searchByPrice(String service, double maxPrice){};

    void joinQueue(String service){};

    List<Listing> searchByProvider(String providerName, String service){};
}
