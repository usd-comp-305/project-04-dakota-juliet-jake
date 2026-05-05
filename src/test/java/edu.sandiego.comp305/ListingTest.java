package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListingTest {
    private Customer customer;

    private ServicerAccount mockServicer;

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

        mockServicer = createMockServicer();

        listing = createListing();
    }

    @Test
    void listingStartsWithNoObservers() {
        assertEquals(0, listing.getServicerObservers().size());
    }

    @Test
    void registerObserverAddsObserverToList() {
        listing.registerObserver(mockServicer);
        assertEquals(1, listing.getServicerObservers().size());
    }

    @Test
    void cantRegisterSameObserverMoreThanOnce() {
        listing.registerObserver(mockServicer);
        listing.registerObserver(mockServicer);
        assertEquals(1, listing.servicerObservers.size());
    }

    @Test
    void removeObserverRemovesObserverFromList() {
        final ServicerAccount mockServicer = mock(ServicerAccount.class);
        listing.registerObserver(mockServicer);
        listing.removeObserver(mockServicer);
        assertEquals(0, listing.servicerObservers.size());
    }


    @Test
    void removeNonexistentObserverDoesNothing() {
        final ServicerAccount mockServicer = mock(ServicerAccount.class);
        final ServicerAccount diffMockServicer = mock(ServicerAccount.class);
        listing.registerObserver(mockServicer);
        listing.removeObserver(diffMockServicer);
        assertEquals(1, listing.servicerObservers.size());
    }


    @Test
    void notifyObserversCallsUpdateOnAllObservers() {
        /*ServicerAccount mockServicer2 = mock(ServicerAccount.class);
        listing.registerObserver(mockServicer);
        listing.registerObserver(mockServicer2);

        listing.notifyObservers(customer.getName(), customer.getAddress(), customer.getSelectedService());

        verify(mockServicer, times(1)).update(listing);*/
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