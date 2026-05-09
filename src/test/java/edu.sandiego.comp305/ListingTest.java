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

    private ServicerAccount.Listing listing;

    private ServiceList mockServiceList;

    private Service shaveService;

    private ArrayList<Service> servicesOffered;

    void createServicesOffered() {
        servicesOffered = new ArrayList<>(List.of(
                new Service("Shave", 20.0),
                new Service("Shear", 25.0)));
    }

    void createShaveService() {
        shaveService = servicesOffered.getFirst();
    }

    void createCustomer() {
        customer = new Customer("Jake", "123 address st");
        customer.selectListing(mockServiceList, 0);
        customer.selectService(0);
    }

    void createMockServicer() {
        mockServicer = mock(ServicerAccount.class);
        when(mockServicer.getName()).thenReturn("Dakota");
        when(mockServicer.getAvailability()).thenReturn("9am-5pm");
        when(mockServicer.getServicesOffered()).thenReturn(servicesOffered);
        when(mockServicer.getGeneralServiceType()).thenReturn("Barber");
    }

    void createListing() {
        listing = mockServicer.new Listing(shaveService);
    }

    @BeforeEach
    void setUp() {
        createServicesOffered();

        createShaveService();

        createMockServicer();

        createListing();

        createCustomer();
    }

    @Test
    void isAvailableIsTrueToStart() {
        assertTrue(listing.getIsAvailable());
    }

    @Test
    void selectedByCustomerCallsUpdate() {
        customer.selectListing(mockServiceList,0);
        customer.selectService(0);

        listing.selectedByCustomer(customer, customer.getSelectedService());

        verify(mockServicer, times(1))
                .update(customer, customer.getSelectedService());
    }

    @Test
    void selectedByCustomerChangesIsAvailable() {
        customer.selectListing(mockServiceList,0);
        customer.selectService(0);

        listing.selectedByCustomer(customer, customer.getSelectedService());

        assertFalse(listing.getIsAvailable());
    }

    @Test
    void listingHasCorrectProviderName() {
        assertEquals("Dakota", listing.getProviderName());
    }

    @Test
    void listingHasCorrectAvailability() {
        assertEquals("9am-5pm", listing.getAvailability());
    }

    @Test
    void listingHasCorrectGeneralServiceType() {
        assertEquals("Barber", listing.getGeneralServiceType());
    }

    @Test
    void listingHasCorrectServicesOffered() {
        //assertEquals(services, listing.getServicesOffered());
    }
}
