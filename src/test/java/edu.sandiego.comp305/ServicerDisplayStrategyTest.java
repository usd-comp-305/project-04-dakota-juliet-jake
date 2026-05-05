package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServicerDisplayStrategyTest {
    @Test
    public void testGetOfferedServices() {
        final ServicerDisplayStrategy strategy = new ServicerDisplayStrategy();
        final Service mockService = Mockito.mock(Service.class);
        final List<Service> services = new ArrayList<>();
        services.add(mockService);
        strategy.setOfferedServices(services);
        assertEquals(1, strategy.getOfferedServices().size());
    }

    @Test
    public void testGetScheduledServices() {
        final ServicerDisplayStrategy strategy = new ServicerDisplayStrategy();
        final Service mockService = Mockito.mock(Service.class);
        final List<Service> services = new ArrayList<>();
        services.add(mockService);
        strategy.setScheduledServices(services);
        assertEquals(1, strategy.getScheduledServices().size());
    }

    @Test
    public void testDisplayShowsServices() {
        final ServicerDisplayStrategy strategy = new ServicerDisplayStrategy();
        final Service mockService = Mockito.mock(Service.class);
        Mockito.when(mockService.getName()).thenReturn("Haircut");
        final List<Service> services = new ArrayList<>();
        services.add(mockService);
        strategy.setOfferedServices(services);
        strategy.display();
        Mockito.verify(mockService).getName();
    }

}