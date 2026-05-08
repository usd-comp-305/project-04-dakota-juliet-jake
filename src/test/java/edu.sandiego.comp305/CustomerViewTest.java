package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
//import java.util.ArrayList;
//import java.util.List;

class CustomerViewTest {

    @Test
    public void testSetStrategy() {
        final CustomerView view =
                new CustomerView(Mockito.mock(DisplayStrategy.class));
        final DisplayStrategy mockStrategy =
                Mockito.mock(DisplayStrategy.class);
        view.setStrategy(mockStrategy);
        view.render();
        Mockito.verify(mockStrategy).display();
    }
}
