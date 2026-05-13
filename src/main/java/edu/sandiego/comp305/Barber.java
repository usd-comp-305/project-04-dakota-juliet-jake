package edu.sandiego.comp305;

import java.util.ArrayList;

public class Barber extends ServicerAccount{

    public Barber(final String name,
                  final String availability,
                  final String generalServiceType,
                  final ArrayList<Service> servicesOffered){
        super(name, availability, generalServiceType, servicesOffered);
    }

    public void shave(){

    }

    public void wax(){

    }

    public void buzz(){

    }

    public void shear(){

    }
}
