package edu.sandiego.comp305;

import java.util.List;

public class Customer extends Profile {
    protected String address;

    protected ServicerAccount.Listing selectedListing;

    public Customer(final String name, final String address) {
        super(name);
        this.address = address;
    }

    protected void selectListing(final ServiceList listings, final int index) {
        selectedListing = listings.getListing(index);
        selectedListing.selectedByCustomer(this);
    }


    protected List<ServicerAccount.Listing> searchByPrice(
            final ServiceList listings,
            final double maxPrice){
        return listings.filterByPrice(maxPrice);
    }

    protected List<ServicerAccount.Listing> searchByService(
            final ServiceList listings,
            final String serviceName){
        try {
            ServiceType serviceType = ServiceType.valueOf(serviceName.toUpperCase().replace(" ", "_"));
            return listings.filterByService(serviceType);
        } catch (IllegalArgumentException e) {
            return List.of();
        }
    }

    public boolean pay(final double amount,
                       final PaymentMethod paymentMethod,
                       final Service service){
        if (amount >= service.getPrice()) {
            return paymentMethod.processPayment(amount);
        }
        return false;
    }

    protected ServicerAccount.Listing getSelectedListing() {
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
        if (this.selectedListing == null) {
            throw new IllegalStateException("No active listing to cancel");
        }
        this.selectedListing = null;
    }
}
