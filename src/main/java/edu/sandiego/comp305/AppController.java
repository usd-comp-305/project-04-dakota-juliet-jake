package edu.sandiego.comp305;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.List;

public class AppController {

    private final View view;

    private final ServiceList serviceList;

    @SuppressFBWarnings(value = "EI_EXPOSE_REP2",
            justification =
                    "Storing references is intentional for MVC controller")
    public AppController(final ServiceList serviceList,
                         final TerminalView view) {
        this.view = view;
        this.serviceList = serviceList;
    }

    public void run() {

        view.display("Welcome to UberCuts! We would like to ask you some questions to get your account set up.");

        final String name = view.prompt("First, please enter your name:");
        final String username = view.prompt("Enter your desired username:");
        final String password = view.prompt("Create your password");

        String accountType = view.prompt(
                "Great start! Now, would you like to create a Customer account or a " +
                        "Servicer account? (Type C for Customer and S for Servicer):");

        while(true) {
            if (accountType.equalsIgnoreCase("C")) {
                handleCustomerFlow(name, username, password);
                break;
            } else if (accountType.equalsIgnoreCase("S")) {
                handleServicerFlow(name, username, password);
                break;
            } else {
                accountType = view.prompt("Invalid selection. Please select C or S.");
            }
        }
    }

    public void handleCustomerFlow(final String name, final String username, String password) {
        final String address = view.prompt(
                "You have selected Customer! Please enter your address so that when " +
                "you book an appointment, our servicers know how to reach you:");

        final Customer customer = new Customer(name, address);
        customer.setUsername(username);
        while(true) {
            try {
                customer.setPassword(password);
                break;
            } catch (IllegalArgumentException e) {
                password = view.prompt(e.getMessage() + "Please re-enter it:");
            }
        }

        browseServiceList(customer);
    }

    public void browseServiceList(Customer customer) {
        view.displayListings(serviceList.getList());

        while (true) {
            final int listingIndex = Integer.parseInt(
                    view.prompt("Enter the number of the listing you want:")) - 1;
            try {
                customer.selectListing(serviceList, listingIndex);
                break;
            } catch (IndexOutOfBoundsException e) {
                view.display("Invalid selection. Please try again.");
            }
        }

        handlePayment(customer);
    }

    public void handleServicerFlow(String name, String username, String password) {
        final String serviceTypeInput = view.prompt(
                "You have selected Servicer! What type of service do you provide? " +
                        "(BARBER, NAIL_TECH, STYLIST):");

        ServiceType serviceType;
        try {
            serviceType = ServiceType.valueOf(serviceTypeInput.toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            view.display("Invalid service type. Please restart.");
            return;
        }

        final String availability = view.prompt(
                "What are your hours of availability? (e.g. 9am-5pm):");

        final ArrayList<Service> services = promptForServices();

        final ServicerAccount servicer = createServicer(name, availability, serviceType, services);
        servicer.setUsername(username);
        servicer.setPassword(password);

        view.display("Account created successfully! Welcome, " + name + ".");

        runServicerMenu(servicer);
    }

    private ArrayList<Service> promptForServices() {
        final ArrayList<Service> services = new ArrayList<>();
        view.display("Let's add the services you offer. Type 'done' when finished.");

        while (true) {
            final String serviceName = view.prompt("Enter service name (or 'done' to finish):");
            if (serviceName.equalsIgnoreCase("done")) {
                if (services.isEmpty()) {
                    view.display("You must add at least one service.");
                    continue;
                }
                break;
            }

            final String priceInput = view.prompt("Enter price for " + serviceName + ":");
            try {
                final double price = Double.parseDouble(priceInput);
                services.add(new Service(serviceName, price));
                view.display(serviceName + " ($" + price + ") added!");
            } catch (NumberFormatException e) {
                view.display("Invalid price. Please try again.");
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
            view.display("\nWhat would you like to do?");
            view.display("1. Post a listing");
            view.display("2. View your listings");
            view.display("3. Update availability");
            view.display("4. Exit");

            final String input = view.prompt("");
            if (input.equals("1")) {
                postListing(servicer);
            } else if (input.equals("2")) {
                viewListings(servicer);
            } else if (input.equals("3")) {
                updateAvailability(servicer);
            } else if (input.equals("4")) {
                view.display("Goodbye, " + servicer.getName() + "!");
                running = false;
            } else {
                view.display("Invalid option. Please try again.");
            }
        }
    }

    private void postListing(final ServicerAccount servicer) {
        view.display("Your services:");
        final ArrayList<Service> services = servicer.getServicesOffered();
        for (int i = 0; i < services.size(); i++) {
            view.display((i + 1) + ". " + services.get(i).getName() +
                    " ($" + services.get(i).getPrice() + ")");
        }

        final String input = view.prompt("Select a service to post a listing for:");
        try {
            final int index = Integer.parseInt(input) - 1;
            final Service selectedService = services.get(index);
            final ServicerAccount.Listing listing = servicer.new Listing(selectedService);
            serviceList.addListing(listing);
            view.display("Listing posted for " + selectedService.getName() + "!");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            view.display("Invalid selection. Please try again.");
        }
    }

    private void viewListings(final ServicerAccount servicer) {
        final List<ServicerAccount.Listing> servicerListings = serviceList.getList()
                .stream()
                .filter(l -> l.getProviderName().equals(servicer.getName()))
                .toList();

        if (servicerListings.isEmpty()) {
            view.display("You have no active listings.");
            return;
        }

        view.display("Your active listings:");
        view.displayListings(servicerListings);
    }

    private void updateAvailability(final ServicerAccount servicer) {
        final String availability = view.prompt(
                "Enter your new availability (e.g. 9am-5pm):");
        servicer.setAvailability(availability);
        view.display("Availability updated to " + availability + "!");
    }

    public void handleSearch(final ServiceType serviceType,
                             final double maxPrice) {
        final List<ServicerAccount.Listing> results =
                serviceList.filterByService(serviceType);
        view.displayListings(results);
    }

    public void handlePayment(Customer customer) {
        final double amountOwed = customer.getSelectedListing()
                .getServiceOffered().getPrice();
        view.display("You owe $" + amountOwed +
                ".");
        while (true) {
            final String paymentType = view.prompt("Enter payment method (CREDIT/CASH/VENMO):");
            PaymentMethod payment = null;
            final PaymentType type;
            try {
                type = PaymentType.valueOf(paymentType
                        .toUpperCase());
                if (type == PaymentType.CASH) {
                    payment = new CashPayment();
                } else if (type == PaymentType.CREDIT) {
                    boolean validLength = false;
                    while (!validLength) {
                        final String cardNumber = view.prompt("Enter your card number:");
                        payment = new CreditCardPayment(cardNumber);
                        try {
                            payment.processPayment(amountOwed);
                            validLength = true;
                        } catch (IllegalArgumentException e) {
                            view.display(e.getMessage());
                        }
                    }

                } else if (type == PaymentType.VENMO) {
                    boolean validHandle = false;
                    while (!validHandle) {
                        final String venmoHandle = view.prompt("Enter your Venmo handle");
                        payment = new VenmoPayment(venmoHandle);
                        try {
                            payment.processPayment(amountOwed);
                            validHandle = true;
                        } catch (IllegalArgumentException e){
                            view.display(e.getMessage());
                        }
                    }

                } else {
                    view.display("Please try again.");
                    continue;
                }
            } catch (IllegalArgumentException e) {
                view.display("Invalid payment method.");
                continue;
            }

            boolean validAmount = false;
            while (!validAmount) {
                final double amount = Double.parseDouble(view.prompt("Please enter the amount " +
                        "you would like to pay:"));
                final boolean paymentSuccess = customer.pay(amount, payment,
                        customer.getSelectedListing().getServiceOffered());
                if (paymentSuccess) {
                    view.display("Payment successful! Thank you, " +
                            "your payment has been accepted and your service " +
                            "has been scheduled.");
                    validAmount = true;
                } else {
                    view.display("Payment failed. Please enter an " +
                            "amount greater than or equal to " +
                            "the service cost.");
                }
            }
            break;

        }
    }

    public void handlePostListing(final ServicerAccount servicer) {
        final String serviceName = view.prompt("Enter the name of the service you want to post:");
        final double price = Double.parseDouble(view.prompt("Enter the price of the service:"));
        final Service service = new Service(serviceName, price);
        final ArrayList<Service> services = servicer.getServicesOffered();
        services.add(service);
        servicer.setServicesOffered(services);
    }

}
