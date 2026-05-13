package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.Scanner;

public class Simulation {

    private Simulation() {
    }

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in,
                java.nio.charset.StandardCharsets.UTF_8);

        final Profile profile = createProfile(scanner);
        final AppController controller = buildController(
                profile, scanner);
        controller.run();
    }

    private static Profile createProfile(final Scanner scanner) {
        System.out.println("Welcome! Please enter your name:");
        final String name = scanner.nextLine();

        final Profile profile = selectAccountType(name, scanner);
        setupCredentials(profile, scanner);

        if (profile instanceof ServicerAccount) {
            setupOfferedServices((ServicerAccount) profile, scanner);
        }
        return profile;
    }

    private static Profile selectAccountType(final String name,
                                             final Scanner scanner) {
        while (true) {
            System.out.println("Would you like to create a Customer" +
                    " or Servicer account? (C or S):");
            final String accountType = scanner.nextLine();
            if (accountType.toUpperCase().equals("C")) {
                System.out.println("Enter your address:");
                return new Customer(name, scanner.nextLine());
            } else if (accountType.toUpperCase().equals("S")) {
                return selectServicerType(name, scanner);
            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    private static ServicerAccount selectServicerType(final String name,
                                                      final Scanner scanner) {
        System.out.println("Enter your availability:");
        final String availability = scanner.nextLine();
        while (true) {
            System.out.println("Please select what type of servicer you are:");
            System.out.println("1. Barber\n" +
                    "2. Nail Technician\n" +
                    "3. Hair Stylist");
            final String serviceType = scanner.nextLine();
            final ArrayList<Service> emptyServices = new ArrayList<>();
            switch (serviceType) {
                case "1" -> {
                    return new Barber(name, availability,
                            ServiceType.BARBER, emptyServices);
                }
                case "2" -> {
                    return new NailTech(name, availability,
                            ServiceType.NAIL_TECH, emptyServices);
                }
                case "3" -> {
                    return new Stylist(name, availability,
                            ServiceType.STYLIST, emptyServices);
                }
                default -> {
                    System.out.println("Invalid selection.");
                }
            }
        }

    }

    private static void setupCredentials(final Profile profile,
                                         final Scanner scanner) {
        boolean validUsername = false;
        while (!validUsername) {
            System.out.println("Enter your desired username:");
            try {
                profile.setUsername(scanner.nextLine());
                validUsername = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        boolean validPassword = false;
        while (!validPassword) {
            System.out.println("Enter your desired password:");
            try {
                profile.setPassword(scanner.nextLine());
                validPassword = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void setupOfferedServices(final ServicerAccount servicer,
                                             final Scanner scanner) {
        final ArrayList<String> availableServices = new ArrayList<>();
        if (servicer instanceof Barber) {
            availableServices.add("Shave");
            availableServices.add("Wax");
            availableServices.add("Buzz");
            availableServices.add("Shear");
        } else if (servicer instanceof NailTech) {
            availableServices.add("Pedi");
            availableServices.add("Mani");
            availableServices.add("Gel Polish");
            availableServices.add("Regular Polish");
        } else if (servicer instanceof Stylist) {
            availableServices.add("Full Dye");
            availableServices.add("Perm");
            availableServices.add("Highlights");
            availableServices.add("Blowout");
        }

        System.out.println("Select the services you offer" +
                " (enter numbers separated by commas):");
        for (int i = 0; i < availableServices.size(); i++) {
            System.out.println((i + 1) + ". " + availableServices.get(i));
        }

        boolean validSelection = false;
        while (!validSelection) {
            try {
                final ArrayList<Service> selectedServices = new ArrayList<>();
                final String[] selections = scanner.nextLine().split(",");
                for (final String selection : selections) {
                    final int index =
                            Integer.parseInt(selection.trim()) - 1;
                    if (index < 0 || index >= availableServices.size()) {
                        throw new IllegalArgumentException(
                                "Invalid selection.");
                    }
                    System.out.println("Enter the price for " +
                            availableServices.get(index) + ":");
                    final double price =
                            Double.parseDouble(scanner.nextLine());
                    selectedServices.add(
                            new Service(availableServices.get(index), price));
                }
                servicer.setServicesOffered(selectedServices);
                validSelection = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Please try again.");
                for (int i = 0; i < availableServices.size(); i++) {
                    System.out.println((i + 1) + ". "
                            + availableServices.get(i));
                }
            }
        }
    }

    private static AppController buildController(final Profile profile,
                                                 final Scanner scanner) {
        final ArrayList<Service> barberServices = new ArrayList<>();
        barberServices.add(new Service("Shave", 20.0));
        barberServices.add(new Service("Haircut", 15.0));
        final Barber barber = new Barber("Jake", "9am-5pm",
                ServiceType.BARBER, barberServices);

        final ArrayList<Service> nailServices = new ArrayList<>();
        nailServices.add(new Service("Pedi", 50.0));
        nailServices.add(new Service("Mani", 30.0));
        final NailTech nailTech = new NailTech("Juliet", "9am-5pm",
                ServiceType.NAIL_TECH, nailServices);

        final ArrayList<Service> stylistServices = new ArrayList<>();
        stylistServices.add(new Service("Full Dye", 150.0));
        final Stylist stylist = new Stylist("Dakota", "9am-5pm",
                ServiceType.STYLIST, stylistServices);

        final CustomerDisplayStrategy customerStrategy =
                new CustomerDisplayStrategy();
        final ServicerDisplayStrategy servicerStrategy =
                new ServicerDisplayStrategy();
        final CustomerView customerView =
                new CustomerView(customerStrategy);
        final ServicerView servicerView =
                new ServicerView(servicerStrategy);

        final ArrayList<ServicerAccount.Listing> listings = new ArrayList<>();
        listings.add(barber.new Listing(barberServices.get(0)));
        listings.add(nailTech.new Listing(nailServices.get(0)));
        listings.add(stylist.new Listing(stylistServices.get(0)));

        final ServiceList serviceList = new ServiceList(listings);

        if (profile instanceof Customer) {
            return new AppController((Customer) profile, barber,
                    customerView, servicerView, serviceList, scanner);
        } else {
            return new AppController(null, (ServicerAccount) profile,
                    customerView, servicerView, serviceList, scanner);
        }
    }
}
