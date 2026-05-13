package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.HashMap;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public abstract class ServicerAccount extends Profile {

    private ArrayList<Service> servicesOffered;

    private ArrayList<Listing> listings;

    private String availability;

    private boolean isAvailable;

    private HashMap<Customer,Service> schedule;

    private String generalServiceType;

    public ServicerAccount(final String name,
                           final String availability,
                           final String generalServiceType,
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

    public String getGeneralServiceType() {

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


    //helper class to make a service postable
    public class Listing {
        private final Service listingService;

        @SuppressFBWarnings(value = "EI2",
                justification = "this$0 is an implicit outer class " +
                        "reference required by the inner class design")
        public Listing(final Service listingService) {
            this.listingService = listingService;
            ServicerAccount.this.isAvailable = true;
        }

        public void selectedByCustomer(final Customer customer) {
            ServicerAccount.this.update(customer, this);
        }

        public String getProviderName(){
            return ServicerAccount.this.getName();
        }

        public Service getServiceOffered() {
            return this.listingService;
        }

        public String getGeneralServiceType() {
            return ServicerAccount.this.getGeneralServiceType();
        }

        public String getAvailability() {
            return ServicerAccount.this.getAvailability();
        }

        public boolean getIsAvailable() {
            return ServicerAccount.this.getIsAvailable();
        }
    }
}
