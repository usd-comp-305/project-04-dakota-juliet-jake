package edu.sandiego.comp305;

import java.util.ArrayList;

public class Simulation {

    private Simulation () {

    }

    public static void main(final String[] args) {
        final ServiceList serviceList = createServiceList();
        final TerminalView terminalView = new TerminalView();
        final AppController controller = new AppController(serviceList,
                terminalView);
        controller.run();
    }

    private static ServiceList createServiceList() {
        final ArrayList<ServicerAccount.Listing> listings = new ArrayList<>();
        listings.addAll(createBarberListings());
        listings.addAll(createNailTechListings());
        listings.addAll(createStylistListings());
        return new ServiceList(listings);
    }

    private static ArrayList<ServicerAccount.Listing> createBarberListings() {
        final ArrayList<Service> services = new ArrayList<>();
        services.add(new Service("Shave", 20.0));
        services.add(new Service("Haircut", 15.0));
        final Barber barber = new Barber("Jake", "9am-5pm",
                ServiceType.BARBER, services);
        final ArrayList<ServicerAccount.Listing> listings = new ArrayList<>();
        for (final Service service : services) {
            listings.add(barber.new Listing(service));
        }
        return listings;
    }

    private static ArrayList<ServicerAccount.Listing> createNailTechListings() {
        final ArrayList<Service> services = new ArrayList<>();
        services.add(new Service("Pedi", 50.0));
        services.add(new Service("Mani", 30.0));
        final NailTech nailTech = new NailTech("Juliet", "9am-5pm",
                ServiceType.NAIL_TECH, services);
        final ArrayList<ServicerAccount.Listing> listings = new ArrayList<>();
        for (final Service service : services) {
            listings.add(nailTech.new Listing(service));
        }
        return listings;
    }

    private static ArrayList<ServicerAccount.Listing> createStylistListings() {
        final ArrayList<Service> services = new ArrayList<>();
        services.add(new Service("Full Dye", 150.0));
        final Stylist stylist = new Stylist("Dakota", "9am-5pm",
                ServiceType.STYLIST, services);
        final ArrayList<ServicerAccount.Listing> listings = new ArrayList<>();
        for (final Service service : services) {
            listings.add(stylist.new Listing(service));
        }
        return listings;
    }
}
