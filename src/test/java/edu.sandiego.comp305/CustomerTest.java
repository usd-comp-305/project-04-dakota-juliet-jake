package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerTest {
    private Customer customer;

    private Listing mockBarberListing;

    private Listing mockStylistListing;

    private ServiceList mockServiceList;

    private PaymentMethod mockPayment;

    private Service mockService;

    Listing createMockBarberListing() {
        final ArrayList<Service> barberServices = new ArrayList<>(List.of(
                new Service("Shave", 20.0),
                new Service("Wax", 30.0),
                new Service("Buzz", 15.0),
                new Service("Shear", 15.0)
        ));

        mockBarberListing = mock(Listing.class);
        when(mockBarberListing.getServicesOffered()).thenReturn(barberServices);
        when(mockBarberListing.isAvailable()).thenReturn(true);
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
        when(mockStylistListing.isAvailable()).thenReturn(true);
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

    @BeforeEach
    void setUp() {
        mockBarberListing = createMockBarberListing();

        mockStylistListing = createMockStylistListing();

        mockServiceList = createMockServiceList();

        mockPayment = createMockPayment();

        mockService = createMockService();

        customer = createCustomer();
    }

    @Test
    void selectListingCorrectlyChangesSelectedListingVariable() {
        final int index = 1;
        customer.selectListing(mockServiceList, index);
        assertEquals(mockStylistListing, customer.getSelectedListing());
    }

    @Test
    void selectListingCorrectlyCallsGetSelectedByInListing() {
        final int index = 1;
        customer.selectListing(mockServiceList, index);
        verify(mockStylistListing, times(1)).getSelectedBy(customer);
    }

    @Test
    void selectListingWithBadIndexThrowsException() {
        final int badIndex = 20;

        when(mockServiceList.getListing(badIndex)).thenThrow(
                new IndexOutOfBoundsException("No Such Index Found"));
        assertThrows(IndexOutOfBoundsException.class, () ->
                customer.selectListing(mockServiceList, badIndex));
        verify(mockServiceList, times(1))
                .getListing(badIndex);
    }

    @Test
    void searchByServiceReturnsCorrectFilteredList() {
        final List<Listing> filteredList =
                customer.searchByService(mockServiceList, "Barber");
        final List<Listing> expectedFilteredList = List.of(mockBarberListing);

        assertEquals(expectedFilteredList, filteredList);
    }

    @Test
    void searchByServiceReturnsEmptyListWhenNoMatches() {
        final List<Listing> filteredList =
                customer.searchByService(mockServiceList, "Nail Tech");
        final List<Listing> expectedFilteredList = List.of();

        assertEquals(expectedFilteredList, filteredList);
    }

    @Test
    void searchByPriceReturnsCorrectFilteredList() {
        final List<Listing> filteredList =
                customer.searchByPrice(mockServiceList, 50.0);
        final List<Listing> expectedFilteredList = List.of(mockBarberListing);

        assertEquals(expectedFilteredList, filteredList);
    }

    @Test
    void searchByPriceReturnsEmptyListWhenNoMatches() {
        final List<Listing> filteredList =
                customer.searchByService(mockServiceList, "Nail Tech");
        final List<Listing> expectedFilteredList = List.of();

        assertEquals(expectedFilteredList, filteredList);
    }

    @Test
    void payReturnsTrueOnSuccessfulPaymentMethod() {
        when(mockPayment.processPayment(50.0)).thenReturn(true);

        assertTrue(customer.pay(50.0, mockPayment, mockService));
    }

    @Test
    void payReturnsFalseOnFailedPaymentMethod() {
        when(mockPayment.processPayment(100.0)).thenReturn(false);

        assertFalse(customer.pay(100.0, mockPayment, mockService));
    }

    @Test
    void payReturnsTrueOnTooMuchMoneyPaid() {
        when(mockPayment.processPayment(70.0)).thenReturn(true);
        when(mockService.getPrice()).thenReturn(35.0);

        assertTrue(customer.pay(70.0, mockPayment, mockService));
    }

    @Test
    void payReturnsFalseOnNotEnoughMoneyPaid() {
        when(mockPayment.processPayment(20.0)).thenReturn(true);
        when(mockService.getPrice()).thenReturn(35.0);

        assertFalse(customer.pay(20.0, mockPayment, mockService));
    }

    @Test
    void payReturnsTrueOnExactMoneyPaid() {
        when(mockPayment.processPayment(35.0)).thenReturn(true);
        when(mockService.getPrice()).thenReturn(35.0);

        assertTrue(customer.pay(35.0, mockPayment, mockService));
    }

    @Test
    void getSelectedListingThrowsExceptionIfNull() {
        assertThrows(IllegalStateException.class, () ->
                customer.getSelectedListing());
    }

    @Test
    void cancelCallMakesSelectedListingNull() {
        final int index = 1;
        customer.selectListing(mockServiceList, index);

        customer.cancelCall();

        assertThrows(IllegalStateException.class, () ->
                customer.getSelectedListing());
    }

    @Test
    void cancelCallOnNoSelectionThrowsException() {
        assertThrows(IllegalStateException.class, () ->
                customer.cancelCall());
    }

    @Test
    void selectServiceCorrectlyChangesSelectedServiceVariable() {
        final int index = 0;

        customer.selectListing(mockServiceList, index);

        customer.selectService(index);

        assertEquals(mockBarberListing.getServicesOffered().getFirst(), customer.getSelectedService());
    }

    @Test
    void selectServiceWithBadIndexThrowsException() {
        final int index = 0;
        final int badIndex = 20;

        customer.selectListing(mockServiceList, index);

        assertThrows(IndexOutOfBoundsException.class, () ->
                customer.selectService(badIndex));
    }



}
