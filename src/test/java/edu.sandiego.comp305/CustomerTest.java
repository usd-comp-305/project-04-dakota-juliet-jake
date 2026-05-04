package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerTest {
    private Customer stdCustomer;
    private ServicerAccount mockServicer;
    private Listing mockBarberListing;
    private Listing mockStylistListing;
    private ServiceList mockServiceList;

    @BeforeEach
    void setUp() {
        mockServicer = mock(ServicerAccount.class);

        ArrayList<Service> barberServices = new ArrayList<>(List.of(
                new Service("Shave", 20.0),
                new Service("Wax", 30.0),
                new Service("Buzz", 15.0),
                new Service("Shear", 15.0)
        ));

        mockBarberListing = mock(Listing.class);
        when(mockBarberListing.getServicesOffered()).thenReturn(barberServices);
        when(mockBarberListing.isAvailable()).thenReturn(true);
        when(mockBarberListing.getProviderName()).thenReturn("Jake");

        ArrayList<Service> stylistServices = new ArrayList<>(List.of(
                new Service("Full Dye", 150.0),
                new Service("Highlights", 100.0)
        ));

        mockStylistListing = mock(Listing.class);
        when(mockStylistListing.getServicesOffered()).thenReturn(stylistServices);
        when(mockStylistListing.isAvailable()).thenReturn(true);
        when(mockStylistListing.getProviderName()).thenReturn("Dakota");

        mockServiceList = mock(ServiceList.class);
        when(mockServiceList.getListing(0)).thenReturn(mockBarberListing);
        when(mockServiceList.getListing(1)).thenReturn(mockStylistListing);
        when(mockServiceList.getList()).thenReturn(new ArrayList<>(List.of(mockBarberListing, mockStylistListing)));

        mockServiceList = new ServiceList(List.of(mockBarberListing, mockStylistListing));
        stdCustomer = new StandardCustomer("123 address st", "user123", "safePass1!", "Jake");
    }

    @Test
    void selectListingCorrectlyChangesSelectedListingVariable() {
        int index = 1;
        stdCustomer.selectListing(mockServiceList, index);
        assertEquals(mockStylistListing, stdCustomer.getSelectedListing());
    }

    @Test
    void selectListingCorrectlyCallsGetSelectedByInListing() {
        int index = 1;
        stdCustomer.selectListing(mockServiceList, index);
        verify(mockStylistListing, times(1)).getSelectedBy(stdCustomer);
    }

    @Test
    void selectListingWithBadIndexThrowsException() {
        ServiceList mockServiceList = mock(ServiceList.class);
        int badIndex = 20;

        when(mockServiceList.getListing(badIndex)).thenThrow(new IndexOutOfBoundsException("No Such Index Found"));
        assertThrows(IndexOutOfBoundsException.class, () -> stdCustomer.selectListing(mockServiceList, badIndex));
        verify(mockServiceList, times(1)).getListing(badIndex);
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

    }

    @Test
    void joinQueue() {
    }

    @Test
    void searchByProvider() {
    }
}
