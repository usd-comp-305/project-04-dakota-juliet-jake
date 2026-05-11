package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListingTest {
    private Customer customer;

    private TestServicerAccount spyServicer;

    private ServicerAccount.Listing shaveListing;

    private ServicerAccount.Listing dyeListing;

    private Service shaveService;

    private Service dyeService;

    private ArrayList<Service> servicesOffered;

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

    void createSpyServicer() {
        spyServicer = spy(new TestServicerAccount(
                "Dakota","9am-5pm", ServiceType.BARBER, servicesOffered));
    }

    void createShaveListing() {
        shaveListing = spyServicer.new Listing(shaveService);
    }

    void createDyeListing() {
        dyeListing = spyServicer.new Listing(dyeService);
    }

    @BeforeEach
    void setUp() {
        createServicesOffered();

        createShaveService();

        createDyeService();

        createSpyServicer();

        createShaveListing();

        createDyeListing();

        createCustomer();
    }

    @Test
    void isAvailableIsTrueToStart() {
        assertTrue(shaveListing.getIsAvailable());
    }

    @Test
    void selectedByCustomerCallsUpdate() {
        shaveListing.selectedByCustomer(customer);

        verify(spyServicer, times(1))
                .update(customer, shaveListing);
    }

    @Test
    void selectedByCustomerChangesIsAvailableToFalse() {
        shaveListing.selectedByCustomer(customer);

        assertFalse(shaveListing.getIsAvailable());
    }

    @Test
    void selectedByCustomerPassesCorrectCustomerToUpdate() {
        final Customer anotherCustomer = new Customer(
                "Juliet", "12 address st");
        shaveListing.selectedByCustomer(anotherCustomer);

        verify(spyServicer, times(1)).update(anotherCustomer, shaveListing);
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
        assertEquals(ServiceType.BARBER, shaveListing.getGeneralServiceType());
    }

    @Test
    void listingHasCorrectService() {
        assertEquals(shaveService.getName(), shaveListing.getServiceName());
    }

    @Test
    void selectingOneListingMakesOtherListingsUnavailable() {
        shaveListing.selectedByCustomer(customer);
        assertFalse(dyeListing.getIsAvailable());
    }

    static class TestServicerAccount extends ServicerAccount {
        public TestServicerAccount(final String name,
                                   final String availability,
                                   final ServiceType generalServiceType,
                                   final ArrayList<Service> servicesOffered) {
            super(name, availability, generalServiceType, servicesOffered);
        }
    }
}
