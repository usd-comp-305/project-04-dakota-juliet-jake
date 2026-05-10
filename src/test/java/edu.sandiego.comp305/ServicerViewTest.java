package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

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
}
