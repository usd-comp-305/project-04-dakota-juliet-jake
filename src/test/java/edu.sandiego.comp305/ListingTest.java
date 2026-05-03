package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListingTest {

    @Test
    void registerObserverAddsObserverToList() {
        final Customer stdCustomer = new StandardCustomer("123 address st", "user123", "safePass1!", "Jake");
        final ServicerAccount mockServicer = mock(ServicerAccount.class);
        stdCustomer.registerObserver(mockServicer);
        assertEquals(1, stdCustomer.servicerObservers.size());
    }


    @Test
    void cantRegisterSameObserverMoreThanOnce() {
        final Customer stdCustomer = new StandardCustomer("123 address st", "user123", "safePass1!", "Jake");
        final ServicerAccount mockServicer = mock(ServicerAccount.class);
        stdCustomer.registerObserver(mockServicer);
        stdCustomer.registerObserver(mockServicer);
        assertEquals(1, stdCustomer.servicerObservers.size());
    }


    @Test
    void removeObserverRemovesObserverFromList() {
        final Customer stdCustomer = new StandardCustomer("123 address st", "user123", "safePass1!", "Jake");
        final ServicerAccount mockServicer = mock(ServicerAccount.class);
        stdCustomer.registerObserver(mockServicer);
        stdCustomer.removeObserver(mockServicer);
        assertEquals(0, stdCustomer.servicerObservers.size());
    }


    @Test
    void removeNonexistentObserverDoesntWork() {
        final Customer stdCustomer = new StandardCustomer("123 address st", "user123", "safePass1!", "Jake");
        final ServicerAccount mockServicer = mock(ServicerAccount.class);
        final ServicerAccount diffMockServicer = mock(ServicerAccount.class);
        stdCustomer.registerObserver(mockServicer);
        stdCustomer.removeObserver(diffMockServicer);
        assertEquals(1, stdCustomer.servicerObservers.size());
    }


    @Test
    void notifyObservers() {
    }


    @Test
    void setProviderName() {
    }

    @Test
    void setServicesOffered() {
    }

    @Test
    void setAvailability() {
    }

    @Test
    void getProviderName() {
    }

    @Test
    void getServicesOffered() {
    }

    @Test
    void getAvailability() {
    }
}