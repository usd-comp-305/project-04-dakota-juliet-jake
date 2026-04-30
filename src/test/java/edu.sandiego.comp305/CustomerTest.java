package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerTest {

    @Test
    void registerObserverAddsObserverToList() {
        final Customer stdCustomer = new StandardCustomer("123 address st");
        final ServicerAccount mockServicer = mock(ServicerAccount.class);
        stdCustomer.registerObserver(mockServicer);
        assertEquals(1, stdCustomer.servicerObservers.size());
    }

    @Test
    void removeObserverRemovesObserverFromList() {
        final Customer stdCustomer = new StandardCustomer("123 address st");
        final ServicerAccount mockServicer = mock(ServicerAccount.class);
        stdCustomer.registerObserver(mockServicer);
        stdCustomer.removeObserver(mockServicer);
        assertEquals(0, stdCustomer.servicerObservers.size());
    }

    @Test
    void notifyObservers() {
    }

    @Test
    void selectService() {
    }

    @Test
    void pay() {
    }

    @Test
    void searchByPrice() {
    }

    @Test
    void joinQueue() {
    }

    @Test
    void searchByProvider() {
    }
}
