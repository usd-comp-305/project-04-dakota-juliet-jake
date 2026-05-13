package edu.sandiego.comp305;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppController {

    private final Customer customer;

    private final ServicerAccount servicer;

    private final CustomerView customerView;

    private final ServicerView servicerView;

    private final ServiceList serviceList;

    private final Scanner scanner;

    @SuppressFBWarnings(value = "EI_EXPOSE_REP2",
            justification =
                    "Storing references is intentional for MVC controller")
    public AppController(final Customer customer,
                         final ServicerAccount servicer,
                         final CustomerView customerView,
                         final ServicerView servicerView,
                         final ServiceList serviceList,
                         final Scanner scanner) {
        this.customer = customer;
        this.servicer = servicer;
        this.customerView = customerView;
        this.servicerView = servicerView;
        this.serviceList = serviceList;
        this.scanner = scanner;
    }

    public void run() {
        boolean isValid = false;
        while (!isValid) {
            System.out.println("Customer or Servicer? (C or S):");
            final String accountType = scanner.nextLine();
            if (accountType.toLowerCase().equals("c")) {
                handleCustomerFlow();
                isValid = true;
            } else if (accountType.toLowerCase().equals("s")) {
                handleServicerFlow();
                isValid = true;
            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    public void handleCustomerFlow() {
        customerView.showServiceList(serviceList.getList());
        System.out.println("Enter the number of the listing you want:");
        final int listingIndex = Integer.parseInt(scanner.nextLine()) - 1;
        customer.selectListing(serviceList, listingIndex);
        servicer.takeCall(customer,
                customer.getSelectedListing().getServiceOffered());
        handlePayment();
    }

    public void handleServicerFlow() {
        servicerView.showOfferedServices(servicer.getServicesOffered());
        servicerView.showSchedule(servicer.getAvailability());
        handlePostListing(servicer);
    }

    public void handleServiceSelection(final Service service,
                                       final ServicerAccount servicer) {
        final ServicerAccount.Listing listing = servicer.new Listing(service);
        listing.selectedByCustomer(customer);
    }

    public void handleSearch(final ServiceType serviceType,
                             final double maxPrice) {
        final List<ServicerAccount.Listing> results =
                serviceList.filterByService(serviceType);
        customerView.showSearchResults(results);
    }

    public void handlePayment() {
        System.out.println("Enter amount to pay:");
        final double amount = Double.parseDouble(scanner.nextLine());

        PaymentMethod payment = null;
        boolean isValid = false;
        while (!isValid) {
            System.out.println("Enter payment method (CREDIT/CASH/VENMO):");
            final String paymentType = scanner.nextLine();

            if (paymentType.toLowerCase().equals("cash")) {
                payment = new CashPayment();
                isValid = true;
            } else if (paymentType.toLowerCase().equals("credit")) {
                System.out.println("Enter your card number:");
                final String cardNumber = scanner.nextLine();
                payment = new CreditCardPayment(cardNumber);
                isValid = true;
            } else if (paymentType.toLowerCase().equals("venmo")) {
                System.out.println("Enter your Venmo handle:");
                final String venmoHandle = scanner.nextLine();
                payment = new VenmoPayment(venmoHandle);
                isValid = true;
            } else {
                System.out.println("Invalid input.");
            }
        }
        final boolean paymentSuccess = customer.pay(amount, payment,
                customer.getSelectedListing().getServiceOffered());
        if (paymentSuccess) {
            System.out.println("Payment successful!");
        } else {
            System.out.println("Payment failed.");
        }
    }

    public void handlePostListing(final ServicerAccount servicer) {
        System.out.println("Enter the name of the service you want to post:");
        final String serviceName = scanner.nextLine();
        System.out.println("Enter the price of the service:");
        final double price = Double.parseDouble(scanner.nextLine());
        final Service service = new Service(serviceName, price);
        final ArrayList<Service> services = servicer.getServicesOffered();
        services.add(service);
        servicer.setServicesOffered(services);
    }

    public void handleScheduleAppointment(final ServiceList serviceList) {
        customerView.showServiceList(serviceList.getList());
        System.out.println("Enter the number of the listing " +
                "you want to schedule:");
        final int listingIndex = Integer.parseInt(scanner.nextLine());
        customer.selectListing(serviceList, listingIndex);
    }
}
