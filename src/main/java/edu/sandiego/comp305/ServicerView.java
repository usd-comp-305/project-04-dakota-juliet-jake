package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;

public class ServicerView {

    private DisplayStrategy strategy;

    public ServicerView(DisplayStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(DisplayStrategy strategy) {
        this.strategy = strategy;
    }

    public void render() {
    }

    public void showOfferedServices(List<Service> services) {
    }

    public void showSchedule(String schedule) {
    }

    public void showNotification(String customerName, String address, Service service) {
    }
}
