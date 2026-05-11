package edu.sandiego.comp305;

import java.util.List;

public class CustomerView {

    private DisplayStrategy strategy;

    public CustomerView(final DisplayStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(final DisplayStrategy strategy) {
        this.strategy = strategy;
    }

    public void render() {
        strategy.display();
    }

    public void showServiceList(final List<ServicerAccount.Listing> listings) {
    public void showServiceList(final List<ServicerAccount.Listing> listings) {
        for (ServicerAccount.Listing listing : listings) {
            System.out.println(listing.getProviderName());
        }
        render();
    }

    public void showSearchResults(final List<ServicerAccount.Listing> results) {
    public void showSearchResults(final List<ServicerAccount.Listing> results) {
        for (ServicerAccount.Listing listing : results) {
            System.out.println(listing.getProviderName());
        }
        render();
    }
}
