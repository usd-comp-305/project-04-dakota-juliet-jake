package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;

public class ServiceList {
    private List<ServicerAccount.Listing> listings;

    public ServiceList(final List<ServicerAccount.Listing> listings) {
        this.listings = new ArrayList<>(listings);
    }

    public ServicerAccount.Listing getListing(final int index){
        return listings.get(index);
    }

    public List<ServicerAccount.Listing> getList(){
        return new ArrayList<>(listings);
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

    public List<ServicerAccount.Listing> filterByPrice(final double maxPrice){
        List<ServicerAccount.Listing> filteredList = new ArrayList<>();
        for (ServicerAccount.Listing listing : listings) {
            if (listing.getPrice() <= maxPrice) {
                filteredList.add(listing);
            }
        }
        return filteredList;
    }
}
