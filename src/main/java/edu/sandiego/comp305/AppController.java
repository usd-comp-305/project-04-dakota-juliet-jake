package edu.sandiego.comp305;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppController {

    private final CustomerView customerView;

    private final ServicerView servicerView;

    private final TerminalView terminalView;

    private final ServiceList serviceList;

    @SuppressFBWarnings(value = "EI_EXPOSE_REP2",
            justification =
                    "Storing references is intentional for MVC controller")
    public AppController(final CustomerView customerView,
                         final ServicerView servicerView,
                         final ServiceList serviceList,
                         final TerminalView terminalView) {
        this.customerView = customerView;
        this.servicerView = servicerView;
        this.terminalView = terminalView;
        this.serviceList = serviceList;
    }

    public void run() {

        terminalView.display("Welcome! We would like to ask you some questions to get your account set up.");

        final String name = terminalView.prompt("First, please enter your name:");
        final String username = terminalView.prompt("Enter your desired username:");
        final String password = terminalView.prompt("Create your password");

        final String accountType = terminalView.prompt(
                "Great start! Now, would you like to create a Customer account or a " +
                        "Servicer account? (Type C for Customer and S for Servicer):");

        if (accountType.equalsIgnoreCase("C")) {
            handleCustomerFlow(name, username, password);
        } else if (accountType.equalsIgnoreCase("S")) {
            handleServicerFlow(name, username, password);
        } else {
            terminalView.display("Invalid selection. Please restart.");
        }
    }

    public void handleCustomerFlow(final String name, final String username, String password) {
        final String address = terminalView.prompt(
                "You have selected Customer! Please enter your address so that when " +
                "you book an appointment, our servicers know how to reach you:");

        final Customer customer = new Customer(name, address);
        customer.setUsername(username);
        while(true) {
            try {
                customer.setPassword(password);
                break;
            } catch (IllegalArgumentException e) {
                password = terminalView.prompt(e.getMessage());
            }
        }

        terminalView.displayListings(serviceList.getList());
        while (true) {
            final int listingIndex = Integer.parseInt(
                    terminalView.prompt("Enter the number of the listing you want:")) - 1;
            try {
                customer.selectListing(serviceList, listingIndex);
                break;
            } catch (IndexOutOfBoundsException e) {
                terminalView.display("Invalid selection. Please try again.");
            }
        }
        handlePayment(customer);
    }

    public void handleServicerFlow(String name, String username, String password) {
        final String serviceTypeInput = terminalView.prompt(
                "You have selected Servicer! What type of service do you provide? " +
                        "(BARBER, NAIL_TECH, STYLIST):");

        ServiceType serviceType;
        try {
            serviceType = ServiceType.valueOf(serviceTypeInput.toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            terminalView.display("Invalid service type. Please restart.");
            return;
        }

        final String availability = terminalView.prompt(
                "What are your hours of availability? (e.g. 9am-5pm):");

        final ArrayList<Service> services = promptForServices();

        final ServicerAccount servicer = createServicer(name, availability, serviceType, services);
        servicer.setUsername(username);
        servicer.setPassword(password);

        terminalView.display("Account created successfully! Welcome, " + name + ".");

        runServicerMenu(servicer);
    }

    private ArrayList<Service> promptForServices() {
        final ArrayList<Service> services = new ArrayList<>();
        terminalView.display("Let's add the services you offer. Type 'done' when finished.");

        while (true) {
            final String serviceName = terminalView.prompt("Enter service name (or 'done' to finish):");
            if (serviceName.equalsIgnoreCase("done")) {
                if (services.isEmpty()) {
                    terminalView.display("You must add at least one service.");
                    continue;
                }
                break;
            }

            final String priceInput = terminalView.prompt("Enter price for " + serviceName + ":");
            try {
                final double price = Double.parseDouble(priceInput);
                services.add(new Service(serviceName, price));
                terminalView.display(serviceName + " ($" + price + ") added!");
            } catch (NumberFormatException e) {
                terminalView.display("Invalid price. Please try again.");
            }
        }

        return services;
    }

    private ServicerAccount createServicer(final String name,
                                           final String availability,
                                           final ServiceType serviceType,
                                           final ArrayList<Service> services) {
        return switch (serviceType) {
            case BARBER -> new Barber(name, availability, serviceType, services);
            case NAIL_TECH -> new NailTech(name, availability, serviceType, services);
            case STYLIST -> new Stylist(name, availability, serviceType, services);
        };
    }

    private void runServicerMenu(final ServicerAccount servicer) {
        boolean running = true;
        while (running) {
            terminalView.display("\nWhat would you like to do?");
            terminalView.display("1. Post a listing");
            terminalView.display("2. View your listings");
            terminalView.display("3. Update availability");
            terminalView.display("4. Exit");

            final String input = terminalView.prompt("");
            if (input.equals("1")) {
                postListing(servicer);
            } else if (input.equals("2")) {
                viewListings(servicer);
            } else if (input.equals("3")) {
                updateAvailability(servicer);
            } else if (input.equals("4")) {
                terminalView.display("Goodbye, " + servicer.getName() + "!");
                running = false;
            } else {
                terminalView.display("Invalid option. Please try again.");
            }
        }
    }

    private void postListing(final ServicerAccount servicer) {
        terminalView.display("Your services:");
        final ArrayList<Service> services = servicer.getServicesOffered();
        for (int i = 0; i < services.size(); i++) {
            terminalView.display((i + 1) + ". " + services.get(i).getName() +
                    " ($" + services.get(i).getPrice() + ")");
        }

        final String input = terminalView.prompt("Select a service to post a listing for:");
        try {
            final int index = Integer.parseInt(input) - 1;
            final Service selectedService = services.get(index);
            final ServicerAccount.Listing listing = servicer.new Listing(selectedService);
            serviceList.addListing(listing);
            terminalView.display("Listing posted for " + selectedService.getName() + "!");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            terminalView.display("Invalid selection. Please try again.");
        }
    }

    private void viewListings(final ServicerAccount servicer) {
        final List<ServicerAccount.Listing> servicerListings = serviceList.getList()
                .stream()
                .filter(l -> l.getProviderName().equals(servicer.getName()))
                .toList();

        if (servicerListings.isEmpty()) {
            terminalView.display("You have no active listings.");
            return;
        }

        terminalView.display("Your active listings:");
        terminalView.displayListings(servicerListings);
    }

    private void updateAvailability(final ServicerAccount servicer) {
        final String availability = terminalView.prompt(
                "Enter your new availability (e.g. 9am-5pm):");
        servicer.setAvailability(availability);
        terminalView.display("Availability updated to " + availability + "!");
    }

    public void handleSearch(final ServiceType serviceType,
                             final double maxPrice) {
        final List<ServicerAccount.Listing> results =
                serviceList.filterByService(serviceType);
        customerView.showSearchResults(results);
    }

    public void handlePayment(Customer customer) {
        while (true) {
            final double amount = Double.parseDouble(terminalView.prompt("Enter amount to pay:"));
            PaymentMethod payment;
            final String paymentType = terminalView.prompt("Enter payment method (CREDIT/CASH/VENMO):");
            final PaymentType type = PaymentType.valueOf(paymentType
                    .toUpperCase());
            if (type == PaymentType.CASH) {
                payment = new CashPayment();
                break;
            } else if (type == PaymentType.CREDIT) {
                final String cardNumber = terminalView.prompt("Enter your card number:");
                payment = new CreditCardPayment(cardNumber);
                break;
            } else if (type == PaymentType.VENMO) {
                final String venmoHandle = terminalView.prompt("Enter your Venmo handle:");
                payment = new VenmoPayment(venmoHandle);
                break;
            } else {
                terminalView.display("Invalid payment method.");
            }
            final boolean paymentSuccess = customer.pay(amount, payment,
                    customer.getSelectedListing().getServiceOffered());
            if (paymentSuccess) {
                terminalView.display("Payment successful!");
                break;
            } else {
                terminalView.display("Payment failed. Please enter an amount " +
                        "greater than or equal to the service cost.");
            }
        }
    }

    public void handlePostListing(final ServicerAccount servicer) {
        final String serviceName = terminalView.prompt("Enter the name of the service you want to post:");
        final double price = Double.parseDouble(terminalView.prompt("Enter the price of the service:"));
        final Service service = new Service(serviceName, price);
        final ArrayList<Service> services = servicer.getServicesOffered();
        services.add(service);
        servicer.setServicesOffered(services);
    }

}
