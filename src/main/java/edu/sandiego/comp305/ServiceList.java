package edu.sandiego.comp305;

import java.util.List;

public class ServiceList {
    List<ServicerAccount.Listing> listings;

    ServiceList(final List<ServicerAccount.Listing> listings) {
        this.listings = listings;
    }

    ServicerAccount.Listing getListing(final int index){
        return listings.get(index);
    }

    List<ServicerAccount.Listing> getList(){
        return listings;
    }

    List<ServicerAccount.Listing> filterByService(final String service){
        return listings;
    }

    List<ServicerAccount.Listing> filterByPrice(final double maxPrice){
        return listings;
    }
}
