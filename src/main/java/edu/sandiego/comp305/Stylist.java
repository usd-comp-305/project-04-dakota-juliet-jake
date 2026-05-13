package edu.sandiego.comp305;

import java.util.ArrayList;

public class Stylist extends ServicerAccount {

    public Stylist(final String name,
                   final String availability,
                   final ServiceType generalServiceType,
                   final ArrayList<Service> servicesOffered){
        super(name, availability, generalServiceType, servicesOffered);
    }

    @Override
    public String performService(final Customer customer) {
        if (!this.getSchedule().containsKey(customer)) {
            throw new IllegalStateException(
                    "No active booking for this customer");
        }
        final Service service = this.getSchedule().get(customer);
        return switch (service.getName().toLowerCase()) {
            case "full dye" -> fullDye();
            case "perm" -> perm();
            case "highlights" -> highlights();
            case "blowout" -> blowout();
            default -> throw new IllegalArgumentException(
                    "Unknown service: " + service.getName());
        };
    }

    public String fullDye(){
        return "\nDying hair...";
    }

    public String perm(){
        return "\nPerming...";
    }

    public String highlights(){
        return "\nAdding highlights...";
    }

    public String blowout(){
        return "\nBlowing out hair...";
    }

}
