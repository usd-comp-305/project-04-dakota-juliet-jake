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
    }

    public void showOfferedServices(final List<Service> services) {
    }

    public void showSchedule(final String schedule) {
    }

    public void showNotification(final String customerName,
                                 final String address, final Service service) {
    }
}
