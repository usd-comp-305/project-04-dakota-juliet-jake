package edu.sandiego.comp305;

import java.util.List;

public class ServicerView {

    private DisplayStrategy strategy;

    public ServicerView(final DisplayStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(final DisplayStrategy strategy) {
        this.strategy = strategy;
    }

    public void render() {
        strategy.display();
    }

    public void showOfferedServices(final List<Service> services) {

        for (Service service : services) {
            System.out.println(service.getName());
        }
    }

    public void showSchedule(final String schedule) {
        System.out.println(schedule);
        render();
    }

    public void showNotification(final String customerName,
                                 final String address, final Service service) {
        System.out.println(customerName + " " + address + " " +
                service.getName());
        render();
    }
}
