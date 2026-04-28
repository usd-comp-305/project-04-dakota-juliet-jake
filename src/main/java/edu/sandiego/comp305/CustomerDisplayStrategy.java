package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;

public class CustomerDisplayStrategy implements DisplayStrategy {

    private List<Service> filteredServices;
    private List<Servicer> filteredServicers;

    public CustomerDisplayStrategy() {
        this.filteredServices = new ArrayList<>();
        this.filteredServicers = new ArrayList<>();
    }

    @Override
    public void display() {
    }

    public void setFilteredResults(List<Service> services, List<Servicer> servicers) {
    }
}
