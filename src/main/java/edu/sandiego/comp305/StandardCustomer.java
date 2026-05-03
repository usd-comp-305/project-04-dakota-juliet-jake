package edu.sandiego.comp305;

public class StandardCustomer extends Customer{
    StandardCustomer(final String address, final String username, final String password, final String name) {
        this.address = address;
        this.selectedListing = null;
        this.username = username;
        this.password = password;
        this.name = name;
        this.rating = 5.0;
    }
}
