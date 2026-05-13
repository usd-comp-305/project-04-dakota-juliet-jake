package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.HashMap;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public abstract class ServicerAccount extends Profile {

    private ArrayList<Service> servicesOffered;

    private ArrayList<Listing> listings;

    private String availability;

    private boolean isAvailable;

    private ServiceType generalServiceType;

    private HashMap<Customer,Service> schedule;

    public ServicerAccount(final String name,
                           final String availability,
                           final ServiceType generalServiceType,
                           final ArrayList<Service> servicesOffered) {
        super(name);
        this.availability = availability;
        this.isAvailable = true;
        this.generalServiceType = generalServiceType;
        this.servicesOffered = new ArrayList<>(servicesOffered);
        this.listings = new ArrayList<>();
        this.schedule = new HashMap<Customer, Service>();
    }

    public void setServicesOffered(final ArrayList<Service> services){
        this.servicesOffered = new ArrayList<>(services);
    }

    public void setAvailability(final String availability){
        this.availability = availability;
    }

    public ArrayList<Service> getServicesOffered() {
        return new ArrayList<>(this.servicesOffered);
    }

    public ServiceType getGeneralServiceType() {
        return this.generalServiceType;
    }

    public String getAvailability() {
        return this.availability;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public HashMap<Customer, Service> getSchedule(){
        return new HashMap<>(this.schedule);
    }

    public ArrayList<Listing> getListings() {
        return new ArrayList<>(listings);
    }

    public void update(final Customer customer,
                       final Listing listing){
        takeCall(customer, listing.listingService);
    }


    public void takeCall(final Customer customer, final Service service){

        this.isAvailable = false;
        this.schedule.put(customer, service);
    }

    public void postService(final Service newService) {
        this.listings.add(new Listing(newService));
    }

    @Override
    public void cancelCall() {
        throw new UnsupportedOperationException(
                "Must provide a customer to cancel a call");
    }

    public void cancelCall(final Customer customer){
        if(!this.schedule.containsKey(customer)) {
            throw new IllegalStateException("Customer has no active booking");
        }
        this.isAvailable = true;
        this.schedule.remove(customer);

    }

    public abstract String performService(final Customer customer);

    protected Service getBookedService(final Customer customer) {
        if (!this.getSchedule().containsKey(customer)) {
            throw new IllegalStateException(
                    "No active booking for this customer");
        }
        return this.getSchedule().get(customer);
    }

    public class Listing {
        private final String serviceName;

        private final Service listingService;

        private final double servicePrice;

        @SuppressFBWarnings(value = "EI2",
                justification = "this$0 is an implicit outer class " +
                        "reference required by the inner class design")
        public Listing(final Service listingService) {
            this.listingService = listingService;
            this.serviceName = listingService.getName();
            this.servicePrice = listingService.getPrice();
            ServicerAccount.this.isAvailable = true;
        }

        public void selectedByCustomer(final Customer customer) {
            ServicerAccount.this.isAvailable = false;
            ServicerAccount.this.update(customer, this);
        }

        public String getProviderName(){
            return ServicerAccount.this.getName();
        }

        public String getServiceName() {
            return this.serviceName;
        }

        public ServiceType getGeneralServiceType() {
            return ServicerAccount.this.getGeneralServiceType();
        }

        public String getAvailability() {
            return ServicerAccount.this.getAvailability();
        }

        public boolean getIsAvailable() {
            return ServicerAccount.this.getIsAvailable();
        }

        public double getPrice() {
            return servicePrice;
        }

        public Service getServiceOffered() {
            return listingService;
        }

        public String performService(final Customer customer) {
            return ServicerAccount.this.performService(customer);
        }
    }
}
