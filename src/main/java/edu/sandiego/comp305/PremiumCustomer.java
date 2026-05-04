package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;

public class PremiumCustomer extends Customer{

    PremiumCustomer(final String address, final String username,
                    final String password, final String name) {
        super(address, username, password, name);
    }

    List<Listing> filterByTime(final int time){
        return new ArrayList<>();
    }
}
