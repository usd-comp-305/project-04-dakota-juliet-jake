package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListingTest {
    private Customer customer;

    private Listing mockBarberListing;

    private Listing mockStylistListing;

    private ServiceList mockServiceList;

    private PaymentMethod mockPayment;

    private Service mockService;

    ServiceList createMockServiceList() {
        mockServiceList = mock(ServiceList.class);
        when(mockServiceList.getListing(0)).thenReturn(mockBarberListing);
        when(mockServiceList.getListing(1)).thenReturn(mockStylistListing);
        when(mockServiceList.getList()).thenReturn(new ArrayList<>(
                List.of(mockBarberListing, mockStylistListing)));
        when(mockServiceList.filterByService("Barber"))
                .thenReturn(List.of(mockBarberListing));
        when(mockServiceList.filterByService("Nail Tech"))
                .thenReturn(List.of());
        when(mockServiceList.filterByPrice(50.0))
                .thenReturn(List.of(mockBarberListing));
        when(mockServiceList.filterByPrice(1.0))
                .thenReturn(List.of());

        return mockServiceList;
    }

    PaymentMethod createMockPayment() {
        return mock(CreditCardPayment.class);
    }

    Service createMockService() {
        return mock(Service.class);
    }

    Customer createCustomer() {
        return new Customer("Jake", "user123",
                "safePass1!","123 address st");
    }

    Listing createListing() {
        return new Listing("Dakota", "MoTuWeThFr", "Barber", )
    }

    @BeforeEach
    void setUp() {
        mockServiceList = createMockServiceList();

        mockPayment = createMockPayment();

        mockService = createMockService();

        customer = createCustomer();
    }

    @Test
    void registerObserverAddsObserverToList() {
        final ServicerAccount mockServicer = mock(ServicerAccount.class);
        customer.registerObserver(mockServicer);
        assertEquals(1, stdCustomer.servicerObservers.size());
    }


    @Test
    void cantRegisterSameObserverMoreThanOnce() {
        final ServicerAccount mockServicer = mock(ServicerAccount.class);
        customer.registerObserver(mockServicer);
        customer.registerObserver(mockServicer);
        assertEquals(1, stdCustomer.servicerObservers.size());
    }


    @Test
    void removeObserverRemovesObserverFromList() {
        final Customer stdCustomer = new Customer("123 address st", "user123", "safePass1!", "Jake");
        final ServicerAccount mockServicer = mock(ServicerAccount.class);
        customer.registerObserver(mockServicer);
        customer.removeObserver(mockServicer);
        assertEquals(0, stdCustomer.servicerObservers.size());
    }


    @Test
    void removeNonexistentObserverDoesntWork() {
        final Customer stdCustomer = new Customer("123 address st", "user123", "safePass1!", "Jake");
        final ServicerAccount mockServicer = mock(ServicerAccount.class);
        final ServicerAccount diffMockServicer = mock(ServicerAccount.class);
        customer.registerObserver(mockServicer);
        customer.removeObserver(diffMockServicer);
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