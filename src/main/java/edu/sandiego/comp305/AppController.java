package edu.sandiego.comp305;

public class AppController {

    private Customer customer;
    private ServicerAccount servicer;
    private CustomerView customerView;
    private ServicerView servicerView;
    private ServiceList serviceList;

    public AppController(Customer customer, ServicerAccount servicer,
                         CustomerView customerView, ServicerView servicerView,
                         ServiceList serviceList) {
        this.customer = customer;
        this.servicer = servicer;
        this.customerView = customerView;
        this.servicerView = servicerView;
        this.serviceList = serviceList;
    }

    public void handleServiceSelection(Service service, ServicerAccount servicer) {
    }

    public void handleSearch(Service service, double maxPrice) {
    }

    public void handlePayment(Payment payment) {
    }

    public void handlePostListing(ServicerAccount servicer) {
    }

    public void handleJoinQueue(Service service) {
    }
}
