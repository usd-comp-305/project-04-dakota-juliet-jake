package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.Scanner;

public class Simulation {

    private Simulation() {
    }

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in,
                java.nio.charset.StandardCharsets.UTF_8);
        System.out.println("Welcome! Please enter your name:");
        final String name = scanner.nextLine();
        System.out.println("Enter your desired username:");
        scanner.nextLine();
        System.out.println("Enter your desired password:");
        scanner.nextLine();
        System.out.println("Enter your address:");
        final String address = scanner.nextLine();

        final Customer customer = new Customer(name, address);

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
        final AppController controller = new AppController(customer,
                barber, customerView, servicerView, serviceList, scanner);
        controller.run();
    }
}
