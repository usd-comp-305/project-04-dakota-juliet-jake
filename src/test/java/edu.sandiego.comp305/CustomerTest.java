package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerTest {

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
    void testNotifyObserversSendsCorrectData() {
        final Customer stdCustomer = new StandardCustomer("123 address st", "user123", "safePass1!", "Jake");        ServicerAccount mockServicer = mock(ServicerAccount.class);
        Service mockService = mock(Service.class);
        stdCustomer.registerObserver(mockServicer);

        stdCustomer.notifyObservers("Alice", "456 Ave", mockService);

        verify(mockServicer).update("Alice", "456 Ave", mockService);
    }

    @Test
    void selectServiceCallsGetListingAndGetSelectedBy() {
        Customer stdCustomer = new StandardCustomer("123 address st", "user123", "safePass1!", "Jake");

        ServiceList mockServiceList = mock(ServiceList.class);
        Listing mockListing = mock(Listing.class);

        when(mockServiceList.getListing(0)).thenReturn(mockListing);

        stdCustomer.selectService(mockServiceList, 0);

        verify(mockListing, times(1)).getSelectedBy(stdCustomer);
    }

    @Test
    void payCallsProcessPayment() {
        Customer stdCustomer = new StandardCustomer("123 address st", "user123", "safePass1!", "Jake");

        Payment mockPayment = mock(Payment.class);

        stdCustomer.pay(mockPayment, PaymentType.VENMO);

        verify(mockPayment, times(1)).processPayment(PaymentType.VENMO);
    }

    @Test
    void searchByPriceCallsFilterByPriceAndReturnsList() {
        Customer stdCustomer = new StandardCustomer("123 address st", "user123", "safePass1!", "Jake");

        ArrayList<Listing> fakeListings;
        fakeListings.add()
        ServiceList mockServiceList = mock(ServiceList.class);
        when(mockServiceList.filterByPrice(50.0)).thenReturn()

        stdCustomer.searchByPrice(mockServiceList, "Barber", 50.0);

        verify()
    }

    @Test
    void joinQueue() {
    }

    @Test
    void searchByProvider() {
    }
}
