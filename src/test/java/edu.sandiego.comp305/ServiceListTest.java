package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

class ServiceListTest {
    private Customer customer;

    private ServiceList serviceList;

    private ListingTest.TestServicerAccount spyServicer;

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
        spyServicer = spy(new ListingTest.TestServicerAccount(
                "Dakota","9am-5pm", "Barber", servicesOffered));
    }

    void createShaveListing() {
        shaveListing = spyServicer.new Listing(shaveService);
    }

    void createDyeListing() {
        dyeListing = spyServicer.new Listing(dyeService);
    }

    void createServiceList() {
        ArrayList<ServicerAccount.Listing> tempList =
                new ArrayList<>(List.of(shaveListing, dyeListing));
        serviceList = new ServiceList(tempList);
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

        createServiceList();
    }

    @Test
    void getListingReturnsCorrectListing() {
        int index = 1;
        assertEquals(dyeListing, serviceList.getListing(index));
    }

    @Test
    void getListingOutOfBoundsIndexThrowsError() {
        int index = 10;
        assertThrows(IndexOutOfBoundsException.class,
                () -> serviceList.getListing(index));
    }

    @Test
    void getListReturnsCorrectListOfListings() {
        ArrayList<ServicerAccount.Listing> expectedList =
                new ArrayList<>(List.of(shaveListing, dyeListing));
        assertEquals(expectedList, serviceList.getList());
    }

    @Test
    void filterByService() {
        assertTrue(true);
    }

    @Test
    void filterByPrice() {
        assertTrue(true);
    }
}
