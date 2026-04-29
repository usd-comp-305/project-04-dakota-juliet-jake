package edu.sandiego.comp305;

public class AppController {

    private Customer customer;

    private ServicerAccount servicer;

    private CustomerView customerView;

    private ServicerView servicerView;

    private ServiceList serviceList;

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

    public void handleServiceSelection(final Service service,
                                       final ServicerAccount servicer) {
    }

    public void handleSearch(final Service service,
                             final double maxPrice) {
    }

    public void handlePayment(final Payment payment) {
    }

    public void handlePostListing(final ServicerAccount servicer) {
    }

    public void handleJoinQueue(final Service service) {
    }
}
