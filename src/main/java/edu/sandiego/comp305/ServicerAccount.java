package edu.sandiego.comp305;

import java.util.ArrayList;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public abstract class ServicerAccount extends Profile {

    private ArrayList<Service> servicesOffered;

    private ArrayList<Listing> listings;

    private String availability;

    private boolean isAvailable;

    private String generalServiceType;

    public ServicerAccount(final String name,
                           final String availability,
                           final String generalServiceType,
                           final ArrayList<Service> servicesOffered) {
        super(name);
        this.availability = availability;
        this.generalServiceType = generalServiceType;
        this.servicesOffered = new ArrayList<>(servicesOffered);
        this.isAvailable = true;
        this.listings = new ArrayList<>();
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

    public ArrayList<Listing> getListings() {
        return new ArrayList<>(listings);
    }

    public void update(final Customer customer,
                       final Listing listing){

    }

    public void takeCall(final Service service){

    }

    public void postService() {

    }

    public void cancelCall(){}

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
            ServicerAccount.this.isAvailable = false;
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
