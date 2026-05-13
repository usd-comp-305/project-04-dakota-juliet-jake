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
        view.display("Welcome to UberCuts! We would like to "
                + "ask you some questions to get your account set up.");

        String name = "";
        String username = "";
        String password = "";

        Profile tempProfile = new Profile() {
            @Override
            public void cancelCall() {

            }
        };

        while (true) {
            try {
                name = view.prompt("First, please enter your name:");
                tempProfile.setName(name);
                break;
            } catch (IllegalArgumentException e) {
                view.display(e.getMessage());
            }
        }

        while (true) {
            try {
                username = view.prompt("Enter your desired username:");
                tempProfile.setUsername(username);
                break;
            } catch (IllegalArgumentException e) {
                view.display(e.getMessage());
            }
        }

        while (true) {
            try {
                password = view.prompt("Create your password:");
                tempProfile.setPassword(password);
                break;
            } catch (IllegalArgumentException e) {
                view.display(e.getMessage());
            }
        }

        String accountType = view.prompt(
                "Great start! Now, would you like to create a "
                        + "Customer account or a Servicer account? "
                        + "(C for Customer and S for Servicer):");

        while (true) {
            if (accountType.equalsIgnoreCase("C")) {
                handleCustomerFlow(name, username, password);
                break;
            } else if (accountType.equalsIgnoreCase("S")) {
                handleServicerFlow(name, username, password);
                break;
            } else {
                accountType = view.prompt(
                        "Invalid selection. Please select C or S.");
            }
        }
    }

    private void handleCustomerFlow(final String name,
                                    final String username,
                                    final String password) {
        final String address = view.prompt(
                "You have selected Customer! Please enter your "
                        + "address so that when you book an appointment, "
                        + "our servicers know how to reach you:");

        final Customer customer = new Customer(name, address);
        customer.setUsername(username);
        String currentPassword = password;
        while (true) {
            try {
                customer.setPassword(currentPassword);
                break;
            } catch (IllegalArgumentException e) {
                currentPassword = view.prompt(
                        e.getMessage() + "Please re-enter it:");
            }
        }

        runCustomerMenu(customer);
    }

    private void browseServiceList(final ServiceList listings,
                                   final Customer customer) {
        view.displayListings(listings.getList());

        while (true) {
            final int listingIndex = Integer.parseInt(
                    view.prompt("Enter the number of the "
                            + "listing you want:")) - 1;
            try {
                customer.selectListing(listings, listingIndex);
                break;
            } catch (IndexOutOfBoundsException e) {
                view.display("Invalid selection. Please try again.");
            }
        }

        view.display("You selected "
                + customer.getSelectedListing().getProviderName()
                + "! Good choice.");

        handlePayment(customer);
    }

    private void handleServicerFlow(final String name,
                                    final String username,
                                    final String password) {
        final String serviceTypeInput = view.prompt(
                "You have selected Servicer! What type of service "
                        + "do you provide? (BARBER, NAIL_TECH, STYLIST):");

        final ServiceType serviceType;
        try {
            serviceType = ServiceType.valueOf(
                    serviceTypeInput.toUpperCase()
                            .replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            view.display("Invalid service type. Please restart.");
            return;
        }

        final String availability = view.prompt(
                "What are your hours of availability? "
                        + "(e.g. 9am-5pm):");

        final ArrayList<Service> services = promptForServices();

        final ServicerAccount servicer = createServicer(
                name, availability, serviceType, services);
        servicer.setUsername(username);
        String currentPassword = password;
        while (true) {
            try {
                servicer.setPassword(currentPassword);
                break;
            } catch (IllegalArgumentException e) {
                currentPassword = view.prompt(
                        e.getMessage() + "Please re-enter it:");
            }
        }

        view.display("Account created successfully! Welcome, "
                + name + ".");

        runServicerMenu(servicer);
    }

    private ArrayList<Service> promptForServices() {
        final ArrayList<Service> services = new ArrayList<>();
        view.display("Let's add the services you offer. "
                + "Type 'done' when finished.");

        while (true) {
            final String serviceName = view.prompt(
                    "Enter service name (or 'done' to finish):");
            if (serviceName.equalsIgnoreCase("done")) {
                if (services.isEmpty()) {
                    view.display(
                            "You must add at least one service.");
                    continue;
                }
                break;
            }

            final String priceInput = view.prompt(
                    "Enter price for " + serviceName + ":");
            try {
                final double price = Double.parseDouble(priceInput);
                services.add(new Service(serviceName, price));
                view.display(serviceName
                        + " ($" + price + ") added!");
            } catch (NumberFormatException e) {
                view.display("Invalid price. Please try again.");
            }
        }

        return services;
    }

    private ServicerAccount createServicer(
            final String name,
            final String availability,
            final ServiceType serviceType,
            final ArrayList<Service> services) {
        return switch (serviceType) {
            case BARBER ->
                    new Barber(name, availability, serviceType, services);
            case NAIL_TECH ->
                    new NailTech(name, availability, serviceType, services);
            case STYLIST ->
                    new Stylist(name, availability, serviceType, services);
        };
    }

    void runServicerMenu(final ServicerAccount servicer) {
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

    void postListing(final ServicerAccount servicer) {
        final ArrayList<Service> services =
                servicer.getServicesOffered();

        view.displayServices(servicer, services);

        final String input = view.prompt(
                "Select a service to post a listing for:");
        try {
            final int index = Integer.parseInt(input) - 1;
            final Service selectedService = services.get(index);
            final ServicerAccount.Listing listing =
                    servicer.new Listing(selectedService);
            serviceList.addListing(listing);
            view.display("Listing posted for "
                    + selectedService.getName() + "!");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            view.display("Invalid selection. Please try again.");
        }
    }

    void viewListings(final ServicerAccount servicer) {
        final List<ServicerAccount.Listing> servicerListings =
                serviceList.getList()
                        .stream()
                        .filter(l -> l.getProviderName()
                                .equals(servicer.getName()))
                        .toList();

        if (servicerListings.isEmpty()) {
            view.display("You have no active listings.");
            return;
        }

        view.display("Your active listings:");
        view.displayListings(servicerListings);
    }

    void updateAvailability(final ServicerAccount servicer) {
        final String availability = view.prompt(
                "Enter your new availability (e.g. 9am-5pm):");
        servicer.setAvailability(availability);
        view.display("Availability updated to " + availability + "!");
    }

    void handleSearch(final ServiceType serviceType,
                      final double maxPrice) {
        final List<ServicerAccount.Listing> results =
                serviceList.filterByService(serviceType);
        view.displayListings(results);
    }

    void handlePayment(final Customer customer) {
        final double amountOwed = customer.getSelectedListing()
                .getServiceOffered().getPrice();
        view.display("You owe $" + amountOwed + ".");
        while (true) {
            final PaymentMethod payment =
                    buildPaymentMethod(amountOwed);
            boolean validAmount = false;
            while (!validAmount) {
                double amount = Double.parseDouble(
                        view.prompt("Please enter the amount "
                                + "you would like to pay:"));
                boolean validNumber = false;
                boolean paymentSuccess = false;
                while (!validNumber) {
                    try {
                        paymentSuccess = customer.pay(amount, payment,
                                customer.getSelectedListing()
                                        .getServiceOffered());
                        validNumber = true;
                    } catch (IllegalArgumentException e) {
                        amount = Double.parseDouble(view.prompt(
                                e.getMessage()
                                        + "Please try again."));
                    }
                }
                if (paymentSuccess) {
                    view.display("Payment successful! Thank you, "
                            + "your payment has been accepted and "
                            + "your service has been scheduled.");
                    validAmount = true;
                } else {
                    view.display("Payment failed. Please enter an "
                            + "amount greater than or equal to "
                            + "the service cost.");
                }
            }
            break;
        }

        runCustomerMenu(customer);
    }

    PaymentMethod buildPaymentMethod(final double amountOwed) {
        while (true) {
            final String paymentType = view.prompt(
                    "Enter payment method (CREDIT/CASH/VENMO):");
            try {
                final PaymentType type = PaymentType.valueOf(
                        paymentType.toUpperCase());
                if (type == PaymentType.CASH) {
                    return new CashPayment();
                } else if (type == PaymentType.CREDIT) {
                    return buildCreditPayment(amountOwed);
                } else if (type == PaymentType.VENMO) {
                    return buildVenmoPayment(amountOwed);
                } else {
                    view.display("Please try again.");
                }
            } catch (IllegalArgumentException e) {
                view.display("Invalid payment method.");
            }
        }
    }

    PaymentMethod buildCreditPayment(final double amountOwed) {
        while (true) {
            final String cardNumber = view.prompt(
                    "Enter your card number:");
            final PaymentMethod payment =
                    new CreditCardPayment(cardNumber);
            try {
                payment.processPayment(amountOwed);
                return payment;
            } catch (IllegalArgumentException e) {
                view.display(e.getMessage());
            }
        }
    }

    PaymentMethod buildVenmoPayment(final double amountOwed) {
        while (true) {
            final String venmoHandle = view.prompt(
                    "Enter your Venmo handle");
            final PaymentMethod payment =
                    new VenmoPayment(venmoHandle);
            try {
                payment.processPayment(amountOwed);
                return payment;
            } catch (IllegalArgumentException e) {
                view.display(e.getMessage());
            }
        }
    }

    public void handlePostListing(final ServicerAccount servicer) {
        final String serviceName = view.prompt(
                "Enter the name of the service you want to post:");
        final double price = Double.parseDouble(
                view.prompt("Enter the price of the service:"));
        final Service service = new Service(serviceName, price);
        final ArrayList<Service> services =
                servicer.getServicesOffered();
        services.add(service);
        servicer.setServicesOffered(services);
    }

    private void runCustomerMenu(final Customer customer) {
        boolean running = true;
        while (running) {
            view.display("\nWhat would you like to do?");
            view.display("1. View all current listings");
            view.display("2. Filter listings by price");
            view.display("3. Filter listings by service type");
            view.display("4. Exit");

            final String input = view.prompt("");
            if (input.equals("1")) {
                browseServiceList(serviceList, customer);
            } else if (input.equals("2")) {
                final ServiceList filteredPriceList =
                        filterServiceListByPrice(customer);
                browseServiceList(filteredPriceList, customer);
            } else if (input.equals("3")) {
                final ServiceList filteredServiceTypeList =
                        filterServiceListByServiceType(customer);
                browseServiceList(filteredServiceTypeList, customer);
            } else if (input.equals("4")) {
                view.display("Goodbye, "
                        + customer.getName() + "!");
                running = false;
            } else {
                view.display("Invalid option. Please try again.");
            }
        }
    }

    private ServiceList filterServiceListByPrice(
            final Customer customer) {
        while (true) {
            try {
                final double maxPrice = Double.parseDouble(
                        view.prompt("Enter the maximum price "
                                + "you would accept:"));
                return new ServiceList(
                        customer.searchByPrice(serviceList, maxPrice));
            } catch (NumberFormatException e) {
                view.display(
                        "Invalid price format. Please try again.");
            }
        }
    }

    private ServiceList filterServiceListByServiceType(
            final Customer customer) {
        while (true) {
            try {
                final String serviceName = view.prompt(
                        "Enter the type of service "
                                + "you are looking for:");
                final ServiceType serviceType = ServiceType.valueOf(
                        serviceName.toUpperCase()
                                .replace(" ", "_"));
                return new ServiceList(customer.searchByService(
                        serviceList, serviceType));
            } catch (IllegalArgumentException e) {
                view.display(
                        "Invalid Service Type. Please try again.");
            }
        }
    }
}
