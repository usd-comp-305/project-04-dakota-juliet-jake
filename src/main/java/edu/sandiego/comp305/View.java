package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;

public interface View {
    void display(final String message);
    String prompt(final String message);
    void displayListings(final List<ServicerAccount.Listing> listings);
}
