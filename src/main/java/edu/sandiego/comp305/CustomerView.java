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
        int index = 1;
        for (ServicerAccount.Listing listing : listings) {
            System.out.printf("%d. %-15s | %-12s | %s ($%.2f)%n",
                    index,
                    listing.getProviderName(),
                    listing.getGeneralServiceType(),
                    listing.getServiceName(),
                    listing.getPrice());
            index++;
        }
        render();
    }

    public void showSearchResults(final List<ServicerAccount.Listing> results) {
        int index = 1;
        for (ServicerAccount.Listing listing : results) {
            System.out.printf("%d. %-15s | %-12s | %s ($%.2f)%n",
                    index,
                    listing.getProviderName(),
                    listing.getGeneralServiceType(),
                    listing.getServiceName(),
                    listing.getPrice());
            index++;
        }
        render();
    }
}
