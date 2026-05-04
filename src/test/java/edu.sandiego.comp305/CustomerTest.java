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
    private PaymentMethod mockPayment;

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
        when(mockServiceList.filterByService("Barber")).thenReturn(List.of(mockBarberListing));
        when(mockServiceList.filterByService("Nail Tech")).thenReturn(List.of());
        when(mockServiceList.filterByPrice(50.0)).thenReturn(List.of(mockBarberListing));
        when(mockServiceList.filterByPrice(1.0)).thenReturn(List.of());

        mockPayment = mock(CreditCardPayment.class);
        when(mockPayment.processPayment(50.0)).thenReturn(true);
        when(mockPayment.processPayment(100.0)).thenReturn(false);


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
        int badIndex = 20;

        when(mockServiceList.getListing(badIndex)).thenThrow(new IndexOutOfBoundsException("No Such Index Found"));
        assertThrows(IndexOutOfBoundsException.class, () -> stdCustomer.selectListing(mockServiceList, badIndex));
        verify(mockServiceList, times(1)).getListing(badIndex);
    }

    @Test
    void searchByServiceReturnsCorrectFilteredList() {
        List<Listing> filteredList = stdCustomer.searchByService(mockServiceList, "Barber");
        List<Listing> expectedFilteredList = List.of(mockBarberListing);

        assertEquals(expectedFilteredList, filteredList);
    }

    @Test
    void searchByServiceReturnsEmptyListWhenNoMatches() {
        List<Listing> filteredList = stdCustomer.searchByService(mockServiceList, "Nail Tech");
        List<Listing> expectedFilteredList = List.of();

        assertEquals(expectedFilteredList, filteredList);
    }

    @Test
    void searchByPriceReturnsCorrectFilteredList() {
        List<Listing> filteredList = stdCustomer.searchByPrice(mockServiceList, 50.0);
        List<Listing> expectedFilteredList = List.of(mockBarberListing);

        assertEquals(expectedFilteredList, filteredList);
    }

    @Test
    void searchByPriceReturnsEmptyListWhenNoMatches() {
        List<Listing> filteredList = stdCustomer.searchByService(mockServiceList, "Nail Tech");
        List<Listing> expectedFilteredList = List.of();

        assertEquals(expectedFilteredList, filteredList);
    }

    @Test
    void payReturnsTrueOnSuccessfulPaymentMethod() {
        Service service = new Service("Shave", 50.0);
        assertTrue(stdCustomer.pay(50.0, mockPayment, service));
    }

    @Test
    void payReturnsFalseOnFailedPaymentMethod() {
        Service service = new Service("Shave", 50.0);
        assertFalse(stdCustomer.pay(100.0, mockPayment, service));
    }

    @Test
    void joinQueue() {
    }

}
