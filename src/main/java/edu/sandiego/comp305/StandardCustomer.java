package edu.sandiego.comp305;

import java.util.ArrayList;

public class StandardCustomer extends Customer{
    StandardCustomer(final String address, final String username, final String password, final String name) {
        this.address = address;
        this.servicerObservers = new ArrayList<>();
        this.selectedService = "";
        this.username = username;
        this.password = password;
        this.name = name;
        this.rating = 5.0;
    }
}
