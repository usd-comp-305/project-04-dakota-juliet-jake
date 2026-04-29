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
    }

    public void showServiceList(final List<Listing> listings) {
    }

    public void showSearchResults(final List<Listing> results) {
    }
}
