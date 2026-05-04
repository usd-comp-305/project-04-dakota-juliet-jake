package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

public class CustomerDisplayStrategyTest {
    @Test
    public void testSetFilteredResults() {
        final CustomerDisplayStrategy strategy = new CustomerDisplayStrategy();
        final Service mockService = Mockito.mock(Service.class);
        final ServicerAccount mockServicer =
                Mockito.mock(ServicerAccount.class);
        final List<Service> services = new ArrayList<>();
        final List<ServicerAccount> servicers = new ArrayList<>();
        services.add(mockService);
        servicers.add(mockServicer);
        strategy.setFilteredResults(services, servicers);
        assertEquals(1, strategy.getFilteredServices().size());
        assertEquals(1, strategy.getFilteredServicers().size());
    }

    @Test
    public void testDisplay() {
        final CustomerDisplayStrategy strategy = new CustomerDisplayStrategy();
        final Service mockService = Mockito.mock(Service.class);
        final ServicerAccount mockServicer =
                Mockito.mock(ServicerAccount.class);
        Mockito.when(mockService.getName()).thenReturn("Haircut");
        final List<Service> services = new ArrayList<>();
        final List<ServicerAccount> servicers = new ArrayList<>();
        services.add(mockService);
        servicers.add(mockServicer);
        strategy.setFilteredResults(services, servicers);
        strategy.display();
        Mockito.verify(mockService).getName();
    }

    @Test
    public void testGetFilteredServices() {
        final CustomerDisplayStrategy strategy = new CustomerDisplayStrategy();
        final Service mockService = Mockito.mock(Service.class);
        final List<Service> services = new ArrayList<>();
        services.add(mockService);
        final List<ServicerAccount> servicers = new ArrayList<>();
        strategy.setFilteredResults(services, servicers);
        assertEquals(1, strategy.getFilteredServices().size());
    }

    @Test
    public void testGetFilteredServicers() {
        final CustomerDisplayStrategy strategy = new CustomerDisplayStrategy();
        final ServicerAccount mockServicer =
                Mockito.mock(ServicerAccount.class);
        final List<Service> services = new ArrayList<>();
        final List<ServicerAccount> servicers = new ArrayList<>();
        servicers.add(mockServicer);
        strategy.setFilteredResults(services, servicers);
        assertEquals(1, strategy.getFilteredServicers().size());
    }
}
