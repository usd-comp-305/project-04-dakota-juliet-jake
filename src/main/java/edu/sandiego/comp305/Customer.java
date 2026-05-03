package edu.sandiego.comp305;

import java.util.List;
import java.util.ArrayList;

public abstract class Customer extends Profile {
    String address;


    String selectedService;

    void selectService(final ServiceList listings, int index){
        Listing chosenService = listings.getListing(index);

        chosenService.getSelectedBy(this);

    }

    void pay(final Payment payment, PaymentType paymentType){
        payment.processPayment(paymentType);
    }

    List<Listing> searchByPrice(final ServiceList listings,
                                final double maxPrice){
        return listings.filterByPrice(maxPrice);
    }

    void joinQueue(final String service){}

    List<Listing> searchByProvider(final String providerName,
                                   final String service){
        return new ArrayList<>();
    }

    String getSelectedService() {
        return this.selectedService;
    }
}
