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

    private ServicerAccount servicer;

    private Service mockService;

    private Listing listing;

    Service createMockService() {
        return mock(Service.class);
    }

    Customer createCustomer() {
        return new Customer("Jake", "user123",
                "safePass1!","123 address st");
    }

    ServicerAccount createMockServicer() {
        return mock(ServicerAccount.class);
    }

    Listing createListing() {
        return new Listing("Dakota",
                "MoTuWeThFr",
                "Barber",
                new ArrayList<Service> (List.of(new Service("Shave", 20.0),
                        new Service("Shear", 25.0))));
    }

    @BeforeEach
    void setUp() {
        mockService = createMockService();

        customer = createCustomer();

        servicer = createMockServicer();

        listing = createListing();
    }

    @Test
    void listingStartsWithNoObservers() {
        assertEquals(0, listing.getServicerObservers().size());
    }

    @Test
    void registerObserverAddsObserverToList() {
        /*final ServicerAccount mockServicer = mock(ServicerAccount.class);
        customer.registerObserver(mockServicer);
        assertEquals(1, stdCustomer.servicerObservers.size());*/
    }


    @Test
    void cantRegisterSameObserverMoreThanOnce() {
        /*final ServicerAccount mockServicer = mock(ServicerAccount.class);
        customer.registerObserver(mockServicer);
        customer.registerObserver(mockServicer);
        assertEquals(1, stdCustomer.servicerObservers.size());*/
    }


    @Test
    void removeObserverRemovesObserverFromList() {
        /*final Customer stdCustomer = new Customer("123 address st", "user123", "safePass1!", "Jake");
        final ServicerAccount mockServicer = mock(ServicerAccount.class);
        customer.registerObserver(mockServicer);
        customer.removeObserver(mockServicer);
        assertEquals(0, stdCustomer.servicerObservers.size());*/
    }


    @Test
    void removeNonexistentObserverDoesntWork() {
        /*final Customer stdCustomer = new Customer("123 address st", "user123", "safePass1!", "Jake");
        final ServicerAccount mockServicer = mock(ServicerAccount.class);
        final ServicerAccount diffMockServicer = mock(ServicerAccount.class);
        customer.registerObserver(mockServicer);
        customer.removeObserver(diffMockServicer);
        assertEquals(1, stdCustomer.servicerObservers.size());*/
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