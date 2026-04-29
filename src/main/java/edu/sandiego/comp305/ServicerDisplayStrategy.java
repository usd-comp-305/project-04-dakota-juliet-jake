package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;

public class ServicerDisplayStrategy implements DisplayStrategy {

    private List<Service> offeredServices;

    private List<Service> scheduledServices;

    public ServicerDisplayStrategy() {
        this.offeredServices = new ArrayList<>();
        this.scheduledServices = new ArrayList<>();
    }

    @Override
    public void display() {
    }

    public void setOfferedServices(final List<Service> services) {
    }

    public void setScheduledServices(final List<Service> services) {
    }
}
