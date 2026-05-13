package edu.sandiego.comp305;

import java.util.ArrayList;

public class Stylist extends ServicerAccount {

    public Stylist(final String name,
                   final String availability,
                   final ServiceType generalServiceType,
                   final ArrayList<Service> servicesOffered){
        super(name, availability, generalServiceType, servicesOffered);
    }

    public void fullDye(){

    }

    public void perm(){

    }

    public void highlights(){

    }

    public void blowout(){

    }

}
