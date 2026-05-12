package edu.sandiego.comp305;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.List;

//import java.util.Scanner;

public class AppController {

    private final Customer customer;

    private final ServicerAccount servicer;

    private final CustomerView customerView;

    private final ServicerView servicerView;

    private final ServiceList serviceList;

    @SuppressFBWarnings(value = "EI_EXPOSE_REP2",
            justification =
                    "Storing references is intentional for MVC controller")
    public AppController(final Customer customer,
                         final ServicerAccount servicer,
                         final CustomerView customerView,
                         final ServicerView servicerView,
                         final ServiceList serviceList) {
        this.customer = customer;
        this.servicer = servicer;
        this.customerView = customerView;
        this.servicerView = servicerView;
        this.serviceList = serviceList;
    }

    public void run() {

    }

    public void handleServiceSelection(final Service service,
                                       final ServicerAccount servicer) {
        final ServicerAccount.Listing listing = servicer.new Listing(service);
        listing.selectedByCustomer(customer);
    }

    public void handleSearch(final Service service, final double maxPrice) {
        final List<ServicerAccount.Listing> results =
                serviceList.filterByService(service.getName());
        customerView.showSearchResults(results);
    }

    public void handlePayment(final PaymentMethod payment) {
    }

    public void handlePostListing(final ServicerAccount servicer) {
    }

    public void handleJoinQueue(final Service service) {
    }
}
