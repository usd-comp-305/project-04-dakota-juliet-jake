package edu.sandiego.comp305;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//import java.util.Scanner;

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
        System.out.println("Welcome! Please enter your name:");
        scanner.nextLine();
        System.out.println("Enter your desired username:");
        scanner.nextLine();
        System.out.println("Enter your desired password:");
        scanner.nextLine();
        System.out.println("Customer or Servicer? (C or S):");
        final String accountType = scanner.nextLine();
        if (accountType.equals("C")) {
            System.out.println("Enter your address:");
            scanner.nextLine();
            customerView.showServiceList(serviceList.getList());
            System.out.println("Enter the number of the listing you want:");
            final int listingIndex = Integer.parseInt(scanner.nextLine());
            customer.selectListing(serviceList, listingIndex);
            System.out.println("Enter amount to pay:");
            final double amount = Double.parseDouble(scanner.nextLine());
            System.out.println("Enter payment method (CREDIT/CASH/VENMO):");
            final String paymentType = scanner.nextLine();
            final PaymentMethod payment;
            if (paymentType.equals("CASH")) {
                payment = new CashPayment();
            } else if (paymentType.equals("CREDIT")) {
                System.out.println("Enter your card number:");
                final String cardNumber = scanner.nextLine();
                payment = new CreditCardPayment(cardNumber);
            } else {
                System.out.println("Enter your Venmo handle:");
                final String venmoHandle = scanner.nextLine();
                payment = new VenmoPayment(venmoHandle);
            }
            final boolean paymentSuccess = customer.pay(amount, payment,
                    customer.getSelectedListing().getServiceOffered());
            if (paymentSuccess) {
                System.out.println("Payment successful!");
            } else {
                System.out.println("Payment failed.");
            }
        } else {
            servicerView.showOfferedServices(servicer.getServicesOffered());
            servicerView.showSchedule(servicer.getAvailability());
            handlePostListing(servicer);
        }
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
        System.out.println("Enter the name of the service you want to post:");
        final String serviceName = scanner.nextLine();
        System.out.println("Enter the price of the service:");
        final double price = Double.parseDouble(scanner.nextLine());
        final Service service = new Service(serviceName, price);
        final ArrayList<Service> services = servicer.getServicesOffered();
        services.add(service);
        servicer.setServicesOffered(services);
    }

    public void handleJoinQueue(final Service service) {
    }
}
