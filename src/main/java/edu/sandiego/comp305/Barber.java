package edu.sandiego.comp305;

import java.util.ArrayList;

public class Barber extends ServicerAccount{

    public Barber(final String name,
                  final String availability,
                  final ServiceType generalServiceType,
                  final ArrayList<Service> servicesOffered){
        super(name, availability, generalServiceType, servicesOffered);
    }

    @Override
    public String performService(final Customer customer) {
        final Service service = getBookedService(customer);
        return switch (service.getName().toLowerCase()) {
            case "shave" -> shave();
            case "wax" -> wax();
            case "buzz" -> buzz();
            case "shear" -> shear();
            default -> throw new IllegalArgumentException(
                    "Unknown service: " + service.getName());
        };
    }

    public String shave(){
        return "\nShaving...";
    }

    public String wax(){
        return "\nWaxing...";
    }

    public String buzz(){
        return "\nBuzzing...";
    }

    public String shear(){
        return "\nShearing...";
    }
}
