package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;

public class PremiumCustomer extends Customer{
    protected PremiumCustomer() {
        super();
    }

    List<Listing> filterByTime(final int time){
        return new ArrayList<>();
    }
}
