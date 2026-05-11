package edu.sandiego.comp305;

import java.util.List;

public class ServiceList {
    private List<ServicerAccount.Listing> listings;

    public ServiceList(final List<ServicerAccount.Listing> listings) {
        this.listings = listings;
    }

    public ServicerAccount.Listing getListing(final int index){
        return listings.get(index);
    }

    public List<ServicerAccount.Listing> getList(){
        return listings;
    }

    public List<ServicerAccount.Listing> filterByService(final String service){
        return listings;
    }

    List<ServicerAccount.Listing> filterByPrice(final double maxPrice){
        return listings;
    }
}
