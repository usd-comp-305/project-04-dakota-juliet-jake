package edu.sandiego.comp305;

import java.util.List;

public class Customer extends Profile {
    protected String address;

    protected Listing selectedListing;

    protected Service selectedService;

    public Customer(final String name, final String username,
                     final String password, final String address) {
        super(name, username, password);
        this.address = address;
    }

    protected void selectListing(final ServiceList listings, final int index) {
        final Listing chosenListing = listings.getListing(index);

        chosenListing.getSelectedBy(this);
        selectedListing = chosenListing;
    }

    protected void selectService(final int index) {
        if (selectedListing == null) {
            throw new IllegalStateException("No Listing has been selected yet");
        }
        if (index >= selectedListing.getServicesOffered().size() || index < 0) {
            throw new IndexOutOfBoundsException("Given index is not in the bounds of the Listing's services");
        }
        selectedService = selectedListing.getServicesOffered().get(index);
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

    protected Service getSelectedService() {
        if (this.selectedListing == null) {
            throw new IllegalStateException("No service has been selected from the listing");
        }
        return this.selectedService;
    }

    protected String getAddress() {
        return this.address;
    }

    @Override
    public void cancelCall() {
        if (this.selectedListing == null) {
            throw new IllegalStateException("No active listing to cancel");
        }
        this.selectedListing = null;
    }
}
