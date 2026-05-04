package edu.sandiego.comp305;

import java.util.List;

public class CustomerDisplayStrategy implements DisplayStrategy {

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
