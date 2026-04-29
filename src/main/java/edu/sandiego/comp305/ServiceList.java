package edu.sandiego.comp305;

import java.util.List;

public class ServiceList {
    List<Listing> listings;

    List<Listing> getListing(){
        return listings;
    }

    List<Listing> filterByService(final String service){
        return listings;
    }

    List<Listing> filterByPrice(final String service, final double maxPrice){
        return listings;
    }
}
