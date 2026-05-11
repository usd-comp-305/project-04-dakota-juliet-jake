package edu.sandiego.comp305;

import java.util.ArrayList;
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

    public List<ServicerAccount.Listing> filterByService(final ServiceType serviceType){
        List<ServicerAccount.Listing> filteredList = new ArrayList<>();
        for (ServicerAccount.Listing listing : listings) {
            if (listing.getGeneralServiceType().equals(serviceType)) {
                filteredList.add(listing);
            }
        }
        return filteredList;
    }

    List<ServicerAccount.Listing> filterByPrice(final double maxPrice){
        return listings;
    }
}
