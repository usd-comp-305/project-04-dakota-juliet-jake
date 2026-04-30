package edu.sandiego.comp305;

import java.util.ArrayList;

public class StandardCustomer extends Customer{
    StandardCustomer(final String address) {
        this.address = address;
        this.servicerObservers = new ArrayList<>();
        this.selectedService = "";
    }
}
