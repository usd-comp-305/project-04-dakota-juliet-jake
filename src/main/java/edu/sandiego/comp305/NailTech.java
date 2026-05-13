package edu.sandiego.comp305;

import java.util.ArrayList;

public class NailTech extends ServicerAccount {

    public NailTech(final String name,
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
            case "pedi" -> pedi();
            case "mani" -> mani();
            case "gel polish" -> gelPolish();
            case "regular polish" -> regularPolish();
            default -> throw new IllegalArgumentException(
                    "Unknown service: " + service.getName());
        };
    }

    public String pedi() {
        return "\nServicing pedicure...";
    }

    public String mani(){
        return "\nServicing manicure...";
    }

    public String gelPolish() {
        return "\nAdding gel polish...";
    }

    public String regularPolish() {
        return "\nAdding regular polish...";
    }
}
