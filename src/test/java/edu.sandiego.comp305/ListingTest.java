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

    private Listing mockBarberListing;

    private Listing mockStylistListing;

    private ServiceList mockServiceList;

    private ArrayList<Service> services;

    Service createMockService() {
        return mock(Service.class);
    }

    Listing createMockBarberListing() {
        final ArrayList<Service> barberServices = new ArrayList<>(List.of(
                new Service("Shave", 20.0),
                new Service("Wax", 30.0),
                new Service("Buzz", 15.0),
                new Service("Shear", 15.0)
        ));

        mockBarberListing = mock(Listing.class);
        when(mockBarberListing.getServicesOffered()).thenReturn(barberServices);
        when(mockBarberListing.getIsAvailable()).thenReturn(true);
        when(mockBarberListing.getProviderName()).thenReturn("Jake");
        when(mockBarberListing.getServicesOffered()).thenReturn(barberServices);

        return mockBarberListing;
    }

    Listing createMockStylistListing() {
        final ArrayList<Service> stylistServices = new ArrayList<>(List.of(
                new Service("Full Dye", 150.0),
                new Service("Highlights", 100.0)
        ));

        mockStylistListing = mock(Listing.class);
        when(mockStylistListing.getServicesOffered())
                .thenReturn(stylistServices);
        when(mockStylistListing.getIsAvailable()).thenReturn(true);
        when(mockStylistListing.getProviderName()).thenReturn("Dakota");

        return mockStylistListing;
    }

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

    Customer createCustomer() {
        customer = new Customer("Jake", "user123",
                "safePass1!","123 address st");
        customer.selectListing(mockServiceList, 0);
        customer.selectService(0);
        return customer;
    }

    ServicerAccount createMockServicer() {
        return mock(ServicerAccount.class);
    }

    Listing createListing() {
        services = new ArrayList<>
                (List.of(new Service("Shave", 20.0),
                        new Service("Shear", 25.0)));
        return new Listing("Dakota",
                "9am-5pm",
                "Barber",
                services,
                mockServicer);
    }

    @BeforeEach
    void setUp() {
        mockService = createMockService();

        mockServicer = createMockServicer();

        listing = createListing();

        mockBarberListing = createMockBarberListing();

        mockStylistListing = createMockStylistListing();

        mockServiceList = createMockServiceList();

        customer = createCustomer();
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

        verify(mockServicer, times(1)).update(customer, customer.getSelectedService());
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
        assertEquals(services, listing.getServicesOffered());
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