package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;

public class CustomerView {

    private DisplayStrategy strategy;

    public CustomerView(DisplayStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(DisplayStrategy strategy) {
        this.strategy = strategy;
    }

    public void render() {
    }

    public void showServiceList(List<Listing> listings) {
    }

    public void showSearchResults(List<Listing> results) {
    }
}
