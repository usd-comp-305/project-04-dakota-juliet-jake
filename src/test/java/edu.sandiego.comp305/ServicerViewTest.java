package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

class ServicerViewTest {

    @Test
    public void testSetStrategy() {
        final ServicerView view =
                new ServicerView(Mockito.mock(DisplayStrategy.class));
        final DisplayStrategy mockStrategy =
                Mockito.mock(DisplayStrategy.class);
        view.setStrategy(mockStrategy);
        view.render();
        Mockito.verify(mockStrategy).display();
    }

    @Test
    public void testRender() {
        final DisplayStrategy mockStrategy =
                Mockito.mock(DisplayStrategy.class);
        final ServicerView view = new ServicerView(mockStrategy);
        view.render();
        Mockito.verify(mockStrategy).display();
    }

    @Test
    public void testShowOfferedServices() {
        final DisplayStrategy mockStrategy =
                Mockito.mock(DisplayStrategy.class);
        final ServicerView view = new ServicerView(mockStrategy);
        final Service mockService = Mockito.mock(Service.class);
        final List<Service> services = new ArrayList<>();
        services.add(mockService);
        view.showOfferedServices(services);
        Mockito.verify(mockStrategy).display();
    }
}
