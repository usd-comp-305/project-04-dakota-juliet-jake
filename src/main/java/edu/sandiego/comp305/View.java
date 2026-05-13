package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;

public interface View {

    public abstract void display(final String message);

    public abstract String prompt(final String message);

    public abstract void displayListings(
            final List<ServicerAccount.Listing> listings);

    public abstract void displayServices(
            final ServicerAccount servicer,
            final ArrayList<Service> services);

}
