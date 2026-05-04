package edu.sandiego.comp305;

import java.util.List;

public class Customer extends Profile {
    protected String address;

    protected Listing selectedListing;

    Customer(final String name, final String username,
                     final String password, final String address) {
        super(name, username, password);
        this.address = address;
    }

    protected void selectListing(final ServiceList listings, final int index) {
        final Listing chosenService = listings.getListing(index);

        chosenService.getSelectedBy(this);
        selectedListing = chosenService;
    }

    protected List<Listing> searchByPrice(final ServiceList listings,
                                          final double maxPrice){
        return listings.filterByPrice(maxPrice);
    }

    protected List<Listing> searchByService(final ServiceList listings,
                                            final String serviceName){
        return listings.filterByService(serviceName);
    }

    public boolean pay(final double amount,
                       final PaymentMethod paymentMethod,
                       final Service service){
        if (amount >= service.getPrice()) {
            return paymentMethod.processPayment(amount);
        }
        return false;
    }

    protected Listing getSelectedListing() {
        if (this.selectedListing == null) {
            throw new IllegalStateException("No listing has been selected");
        }
        return this.selectedListing;
    }

    protected String getAddress() {
        return this.address;
    }

    @Override
    public void cancelCall() {
        this.selectedListing = null;
    }
}
