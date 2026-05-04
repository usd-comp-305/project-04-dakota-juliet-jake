package edu.sandiego.comp305;

import java.util.List;
import java.util.ArrayList;

public abstract class Customer extends Profile {
    String address;

    Listing selectedListing;

    void selectListing(final ServiceList listings, int index) {
        Listing chosenService = listings.getListing(index);

        chosenService.getSelectedBy(this);
        selectedListing = chosenService;
    }

    List<Listing> searchByPrice(final ServiceList listings,
                                final double maxPrice){
        return listings.filterByPrice(maxPrice);
    }

    List<Listing> searchByService(final ServiceList listings,
                                  final String serviceName){
        return listings.filterByService(serviceName);
    }

    void joinQueue(final String service){}

    void pay(final Payment payment, PaymentType paymentType){
        payment.processPayment(paymentType);
    }

    Listing getSelectedListing() {
        return this.selectedListing;
    }
}
