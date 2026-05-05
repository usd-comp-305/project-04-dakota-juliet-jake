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
        for (Service service : offeredServices) {
            System.out.println(service.getName());
        }
        for (Service service : scheduledServices) {
            System.out.println(service.getName());
        }
    }

    public List<Service> getOfferedServices() {
        return new ArrayList<>(offeredServices);
    }

    public void setOfferedServices(final List<Service> services) {
        this.offeredServices = new ArrayList<>(services);
    }

    public List<Service> getScheduledServices() {
        return new ArrayList<>(scheduledServices);
    }

    public void setScheduledServices(final List<Service> services) {
        this.scheduledServices = new ArrayList<>(services);
    }
}
