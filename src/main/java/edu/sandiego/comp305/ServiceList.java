package edu.sandiego.comp305;

import java.util.List;

public class ServiceList {
    List<Listing> listings;

    ServiceList(final List<Listing> listings) {
        this.listings = listings;
    }

    Listing getListing(int index){
        return listings.get(index);
    }

    List<Listing> getList(){
        return listings;
    }

    List<Listing> filterByService(final String service){
        return listings;
    }

    List<Listing> filterByPrice(final double maxPrice){
        return listings;
    }
}
