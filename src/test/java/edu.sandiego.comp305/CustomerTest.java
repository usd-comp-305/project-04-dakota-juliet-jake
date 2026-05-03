package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerTest {

    @BeforeEach
    void setUp() {
        ServicerAccount mockServicer = mock(ServicerAccount.class);

        // Barber listing with multiple services
        ArrayList<Service> barberServices = new ArrayList<>(List.of(
                new Service("Shave", 20.0),
                new Service("Wax", 30.0),
                new Service("Buzz", 15.0),
                new Service("Shear", 15.0)
        ));

        Listing barberListing = mock(Listing.class);
        when(barberListing.getServicesOffered()).thenReturn(barberServices);
        when(barberListing.isAvailable()).thenReturn(true);
        when(barberListing.getProviderName()).thenReturn("Jake");

        // Stylist listing with different services
        ArrayList<Service> stylistServices = new ArrayList<>(List.of(
                new Service("Full Dye", 150.0),
                new Service("Highlights", 100.0)
        ));

        Listing stylistListing = mock(Listing.class);
        when(stylistListing.getServicesOffered()).thenReturn(stylistServices);
        when(stylistListing.isAvailable()).thenReturn(true);
        when(stylistListing.getProviderName()).thenReturn("Dakota");

        ServiceList serviceList = new ServiceList(List.of(barberListing, stylistListing));
        Customer stdCustomer = new StandardCustomer("123 address st", "user123", "safePass1!", "Jake");
    }

    @Test
    void selectServiceCallsGetListingAndGetSelectedBy() {

        ServiceList mockServiceList = mock(ServiceList.class);
        Listing mockListing = mock(Listing.class);

        int index = 1;

        when(mockServiceList.getListing(index)).thenReturn(mockListing);

        stdCustomer.selectService(mockServiceList, index);

        verify(mockListing, times(1)).getSelectedBy(stdCustomer);
    }

    @Test
    void selectServiceWithBadIndex() {
        Customer stdCustomer = new StandardCustomer("123 address st", "user123", "safePass1!", "Jake");

        ServiceList mockServiceList = mock(ServiceList.class);
        Listing mockListing = mock(Listing.class);

        int badIndex = 99;

        when(mockServiceList.getListing(badIndex)).thenThrow(new IndexOutOfBoundsException("the chosen index does not exist"));

        assertThrows(IndexOutOfBoundsException.class, () -> stdCustomer.selectService(mockServiceList, badIndex));
        verify(mockServiceList).getListing(badIndex);
        verifyNoMoreInteractions(mockServiceList);
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
/*        Customer stdCustomer = new StandardCustomer("123 address st", "user123", "safePass1!", "Jake");

        ArrayList<Listing> fakeListings;
        fakeListings.add()
        ServiceList mockServiceList = mock(ServiceList.class);
        when(mockServiceList.filterByPrice(50.0)).thenReturn()

        stdCustomer.searchByPrice(mockServiceList, "Barber", 50.0);

        verify()*/
    }

    @Test
    void joinQueue() {
    }

    @Test
    void searchByProvider() {
    }
}
