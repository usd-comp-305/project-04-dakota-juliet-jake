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

    private String shave(){
        return "\nShaving...";
    }

    private String wax(){
        return "\nWaxing...";
    }

    private String buzz(){
        return "\nBuzzing...";
    }

    private String shear(){
        return "\nShearing...";
    }
}
