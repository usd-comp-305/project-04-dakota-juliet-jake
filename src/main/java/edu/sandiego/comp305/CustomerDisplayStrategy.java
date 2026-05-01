package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;

public class CustomerDisplayStrategy implements DisplayStrategy {

    private List<Service> filteredServices;

    private List<ServicerAccount> filteredServicers;

    public CustomerDisplayStrategy() {
        this.filteredServices = new ArrayList<>();
        this.filteredServicers = new ArrayList<>();
    }

    @Override
    public void display() {
        for (Service service : filteredServices) {
            System.out.println(service.getName());
        }
        for (ServicerAccount servicer : filteredServicers) {
            System.out.println(servicer);
        }
    }


    public List<Service> getFilteredServices() {

        return new ArrayList<>(filteredServices);
    }

    public List<ServicerAccount> getFilteredServicers() {

        return new ArrayList<>(filteredServicers);
    }

    public void setFilteredResults(final List<Service> services,
                                   final List<ServicerAccount> servicers) {
        this.filteredServices = new ArrayList<>(services);
        this.filteredServicers = new ArrayList<>(servicers);
    }
}
