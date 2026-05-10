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

    private ServicerAccount.Listing shaveListing;

    private ServicerAccount.Listing dyeListing;

    private ServiceList mockServiceList;

    private Service shaveService;

    private Service dyeService;

    private ArrayList<Service> servicesOffered;

    void createMockServiceList() {
        mockServiceList = mock(ServiceList.class);
        when(mockServiceList.getListing(0)).thenReturn(shaveListing);
        when(mockServiceList.getList()).thenReturn(new ArrayList<>(
                List.of(shaveListing)));
        when(mockServiceList.filterByService("Barber"))
                .thenReturn(List.of(shaveListing));
        when(mockServiceList.filterByService("Stylist"))
                .thenReturn(List.of(dyeListing));
        when(mockServiceList.filterByPrice(50.0))
                .thenReturn(List.of(shaveListing));
        when(mockServiceList.filterByPrice(1.0))
                .thenReturn(List.of());
    }

    void createServicesOffered() {
        servicesOffered = new ArrayList<>(List.of(
                new Service("Shave", 20.0),
                new Service("Shear", 25.0)));
    }

    void createShaveService() {
        shaveService = servicesOffered.getFirst();
    }

    void createDyeService() {
        dyeService = new Service("Full Dye", 150.0);
    }

    void createCustomer() {
        customer = new Customer("Jake", "123 address st");
    }

    void createMockServicer() {
        mockServicer = mock(ServicerAccount.class);
        when(mockServicer.getName()).thenReturn("Dakota");
        when(mockServicer.getAvailability()).thenReturn("9am-5pm");
        when(mockServicer.getServicesOffered()).thenReturn(servicesOffered);
        when(mockServicer.getGeneralServiceType()).thenReturn("Barber");
        when(mockServicer.getIsAvailable()).thenReturn(true);
    }

    void createShaveListing() {
        shaveListing = mockServicer.new Listing(shaveService);
    }

    void createDyeListing() {
        dyeListing = mockServicer.new Listing(dyeService);
    }

    @BeforeEach
    void setUp() {
        createServicesOffered();

        createShaveService();

        createDyeService();

        createMockServicer();

        createShaveListing();

        createDyeListing();

        createMockServiceList();

        createCustomer();
    }

    @Test
    void isAvailableIsTrueToStart() {
        assertTrue(shaveListing.getIsAvailable());
    }

    @Test
    void selectedByCustomerCallsUpdate() {
        customer.selectListing(mockServiceList,0);

        shaveListing.selectedByCustomer(customer);

        verify(mockServicer, times(1))
                .update(customer, customer.getSelectedListing());
    }

    @Test
    void selectedByCustomerChangesIsAvailable() {
        customer.selectListing(mockServiceList,0);

        shaveListing.selectedByCustomer(customer);

        assertFalse(shaveListing.getIsAvailable());
    }

    @Test
    void listingHasCorrectProviderName() {
        assertEquals("Dakota", shaveListing.getProviderName());
    }

    @Test
    void listingHasCorrectAvailability() {
        assertEquals("9am-5pm", shaveListing.getAvailability());
    }

    @Test
    void listingHasCorrectGeneralServiceType() {
        assertEquals("Barber", shaveListing.getGeneralServiceType());
    }

    @Test
    void listingHasCorrectServicesOffered() {
        //assertEquals(services, barberListing.getServicesOffered());
    }
}
