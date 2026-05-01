package edu.sandiego.comp305;

import java.util.List;

public class ServiceList {
    List<Listing> listings;

    Listing getListing(int index){
        return listings.get(index);
    }

    List<Listing> filterByService(final String service){
        return listings;
    }

    List<Listing> filterByPrice(final String service, final double maxPrice){
        return listings;
    }
}
