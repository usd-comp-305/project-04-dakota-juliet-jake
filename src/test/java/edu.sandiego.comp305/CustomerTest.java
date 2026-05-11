package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerTest {
    private Customer customer;

    private ServicerAccount.Listing mockBarberListing;

    private ServicerAccount.Listing mockStylistListing;

    private ServiceList mockServiceList;

    private PaymentMethod mockPayment;

    private Service mockService;

    ServicerAccount.Listing createMockBarberListing() {
        final Service barberService = new Service("Shave", 20.0);

        mockBarberListing = mock(ServicerAccount.Listing.class);
        when(mockBarberListing.getServiceOffered()).thenReturn(barberService);
        when(mockBarberListing.getIsAvailable()).thenReturn(true);
        when(mockBarberListing.getProviderName()).thenReturn("Jake");

        return mockBarberListing;
    }

    ServicerAccount.Listing createMockStylistListing() {
        final Service stylistService = new Service("Full Dye", 150.0);

        mockStylistListing = mock(ServicerAccount.Listing.class);
        when(mockStylistListing.getServiceOffered())
                .thenReturn(stylistService);
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
        when(mockServiceList.filterByService(ServiceType.BARBER))
                .thenReturn(List.of(mockBarberListing));
        when(mockServiceList.filterByService(ServiceType.NAIL_TECH))
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
        final Customer customer = new Customer("Jake", "123 address st");
        customer.setUsername("user123");
        customer.setPassword("safePass1!");
        return customer;
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
        verify(mockStylistListing, times(1))
                .selectedByCustomer(customer);
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
        final List<ServicerAccount.Listing> filteredList =
                customer.searchByService(mockServiceList, "Barber");
        final List<ServicerAccount.Listing> expectedFilteredList =
                List.of(mockBarberListing);

        assertEquals(expectedFilteredList, filteredList);
    }

    @Test
    void searchByServiceReturnsEmptyListWhenNoMatches() {
        final List<ServicerAccount.Listing> filteredList =
                customer.searchByService(mockServiceList, "Nail Tech");
        final List<ServicerAccount.Listing> expectedFilteredList = List.of();

        assertEquals(expectedFilteredList, filteredList);
    }

    @Test
    void searchByPriceReturnsCorrectFilteredList() {
        final List<ServicerAccount.Listing> filteredList =
                customer.searchByPrice(mockServiceList, 50.0);
        final List<ServicerAccount.Listing> expectedFilteredList =
                List.of(mockBarberListing);

        assertEquals(expectedFilteredList, filteredList);
    }

    @Test
    void searchByPriceReturnsEmptyListWhenNoMatches() {
        final List<ServicerAccount.Listing> filteredList =
                customer.searchByService(mockServiceList, "Nail Tech");
        final List<ServicerAccount.Listing> expectedFilteredList = List.of();

        assertEquals(expectedFilteredList, filteredList);
    }

    @Test
    void payReturnsTrueOnSuccessfulPaymentMethod() {
        when(mockService.getPrice()).thenReturn(35.0);
        when(mockPayment.processPayment(50.0)).thenReturn(true);

        assertTrue(customer.pay(50.0, mockPayment, mockService));
    }

    @Test
    void payReturnsFalseOnFailedPaymentMethod() {
        when(mockService.getPrice()).thenReturn(35.0);
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

}
