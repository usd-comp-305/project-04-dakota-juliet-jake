package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;

public class PremiumCustomer extends Customer{

    PremiumCustomer(final String name, final String address) {
        super(name, address);
    }

    List<Listing> filterByTime(final int time){
        return new ArrayList<>();
    }
}
