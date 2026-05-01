package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

public class CustomerDisplayStrategyTest {
    @Test
    public void testSetFilteredResults() {
        CustomerDisplayStrategy strategy = new CustomerDisplayStrategy();
        Service mockService = Mockito.mock(Service.class);
        ServicerAccount mockServicer = Mockito.mock(ServicerAccount.class);
        List<Service> services = new ArrayList<>();
        List<ServicerAccount> servicers = new ArrayList<>();
        services.add(mockService);
        servicers.add(mockServicer);
        strategy.setFilteredResults(services, servicers);
        assertEquals(1, strategy.getFilteredServices().size());
        assertEquals(1, strategy.getFilteredServicers().size());
    }

    @Test
    public void testDisplay() {
        CustomerDisplayStrategy strategy = new CustomerDisplayStrategy();
        Service mockService = Mockito.mock(Service.class);
        ServicerAccount mockServicer = Mockito.mock(ServicerAccount.class);
        Mockito.when(mockService.getName()).thenReturn("Haircut");
        List<Service> services = new ArrayList<>();
        List<ServicerAccount> servicers = new ArrayList<>();
        services.add(mockService);
        servicers.add(mockServicer);
        strategy.setFilteredResults(services, servicers);
        strategy.display();
        Mockito.verify(mockService).getName();
    }

    @Test
    public void testGetFilteredServices() {
        CustomerDisplayStrategy strategy = new CustomerDisplayStrategy();
        Service mockService = Mockito.mock(Service.class);
        List<Service> services = new ArrayList<>();
        services.add(mockService);
        List<ServicerAccount> servicers = new ArrayList<>();
        strategy.setFilteredResults(services, servicers);
        assertEquals(1, strategy.getFilteredServices().size());
    }

    @Test
    public void testGetFilteredServicers() {
        CustomerDisplayStrategy strategy = new CustomerDisplayStrategy();
        ServicerAccount mockServicer = Mockito.mock(ServicerAccount.class);
        List<Service> services = new ArrayList<>();
        List<ServicerAccount> servicers = new ArrayList<>();
        servicers.add(mockServicer);
        strategy.setFilteredResults(services, servicers);
        assertEquals(1, strategy.getFilteredServicers().size());
    }
}
