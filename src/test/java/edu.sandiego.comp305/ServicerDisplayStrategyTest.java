package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

public class ServicerDisplayStrategyTest {
    @Test
    public void testGetOfferedServices() {
        final ServicerDisplayStrategy strategy = new ServicerDisplayStrategy();
        final Service mockService = Mockito.mock(Service.class);
        final List<Service> services = new ArrayList<>();
        services.add(mockService);
        strategy.setOfferedServices(services);
        assertEquals(1, strategy.getOfferedServices().size());
    }
}
