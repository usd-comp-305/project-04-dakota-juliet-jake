package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.Scanner;

public class Simulation {

    private Simulation() {
    }

    public static void main(final String[] args) {

        Profile profile = null;
        final Scanner scanner = new Scanner(System.in,
                java.nio.charset.StandardCharsets.UTF_8);

        System.out.println("Welcome! Please enter your name:");
        final String name = scanner.nextLine();

        // account type selection and profile creation
        boolean isValid = false;
        while (!isValid) {
            System.out.println("Would you like to create a Customer" +
                    " or Servicer account? (C or S):");
            final String accountType = scanner.nextLine();
            if (accountType.toUpperCase().equals("C")) {
                System.out.println("Enter your address:");
                final String address = scanner.nextLine();
                profile = new Customer(name, address);
                isValid = true;
            } else if (accountType.toUpperCase().equals("S")) {
                System.out.println("Enter your availability (ex: 9am-5pm):");
                final String availability = scanner.nextLine();
                System.out.println("Please select what type " +
                        "of servicer you are:");
                System.out.println("1. Barber\n" +
                        "2. Nail Technician\n" +
                        "3. Hair Stylist");
                final String serviceType = scanner.nextLine();
                final ArrayList<Service> emptyServices = new ArrayList<>();
                switch (serviceType) {
                    case "1" -> profile = new Barber(name, availability,
                            ServiceType.BARBER, emptyServices);
                    case "2" -> profile = new NailTech(name, availability,
                            ServiceType.NAIL_TECH, emptyServices);
                    case "3" -> profile = new Stylist(name, availability,
                            ServiceType.STYLIST, emptyServices);
                    default -> {
                        System.out.println("Invalid selection.");
                        continue;
                    }
                }
                isValid = true;
            } else {
                System.out.println("Invalid input.");
            }
        }

        // username setup with validation
        boolean validUsername = false;
        while (!validUsername) {
            System.out.println("Enter your desired username:");
            final String username = scanner.nextLine();
            try {
                profile.setUsername(username);
                validUsername = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // password setup with validation
        boolean validPassword = false;
        while (!validPassword) {
            System.out.println("Enter your desired password:");
            final String password = scanner.nextLine();
            try {
                profile.setPassword(password);
                validPassword = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // servicer offered services setup
        if (profile instanceof ServicerAccount) {
            final ServicerAccount servicerProfile = (ServicerAccount) profile;

            final ArrayList<String> availableServices = new ArrayList<>();
            if (profile instanceof Barber) {
                availableServices.add("Shave");
                availableServices.add("Wax");
                availableServices.add("Buzz");
                availableServices.add("Shear");
            } else if (profile instanceof NailTech) {
                availableServices.add("Pedi");
                availableServices.add("Mani");
                availableServices.add("Gel Polish");
                availableServices.add("Regular Polish");
            } else if (profile instanceof Stylist) {
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

            final ArrayList<Service> selectedServices = new ArrayList<>();
            boolean validSelection = false;
            while (!validSelection) {
                try {
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
                        selectedServices.add(new Service(
                                availableServices.get(index), price));
                    }
                    servicerProfile.setServicesOffered(
                            selectedServices);
                    validSelection = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage() + " Please try again.");
                    //clear in case partial selections were added
                    selectedServices.clear();
                    for (int i = 0; i < availableServices.size(); i++) {
                        System.out.println((i + 1) + ". "
                                + availableServices.get(i));
                    }
                }
            }
        }

        // hardcoded servicers for the service list
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

        // run the app with the right profile type
        if (profile instanceof Customer) {
            final AppController controller = new AppController(
                    (Customer) profile, barber, customerView,
                    servicerView, serviceList, scanner);
            controller.run();
        } else if (profile instanceof ServicerAccount) {
            final AppController controller = new AppController(
                    null, (ServicerAccount) profile, customerView,
                    servicerView, serviceList, scanner);
            controller.run();
        }
    }
}
