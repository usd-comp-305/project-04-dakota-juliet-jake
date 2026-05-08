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

    public void showServiceList(final List<Listing> listings) {
        for (Listing listing : listings) {
            System.out.println(listing.getProviderName());
        }
        render();
    }

    public void showSearchResults(final List<Listing> results) {
    }
}
