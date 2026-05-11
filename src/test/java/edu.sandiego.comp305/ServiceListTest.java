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

    private ListingTest.TestServicerAccount spyBarberServicer;

    private ListingTest.TestServicerAccount spyStylistServicer;


    private ServicerAccount.Listing shaveListing;

    private ServicerAccount.Listing dyeListing;

    private Service shaveService;

    private Service dyeService;

    private ArrayList<Service> barberServicesOffered;

    private ArrayList<Service> stylistServicesOffered;

    void createBarberServicesOffered() {
        barberServicesOffered = new ArrayList<>(List.of(
                new Service("Shave", 20.0),
                new Service("Shear", 25.0)));
    }

    void createStylistServicesOffered() {
        stylistServicesOffered = new ArrayList<>(List.of(
                new Service("Full Dye", 150.0),
                new Service("Highlights", 120.0)));
    }

    void createShaveService() {
        shaveService = barberServicesOffered.getFirst();
    }

    void createDyeService() {
        dyeService = stylistServicesOffered.getFirst();
    }

    void createCustomer() {
        customer = new Customer("Jake", "123 address st");
    }

    void createSpyBarberServicer() {
        spyBarberServicer = spy(new ListingTest.TestServicerAccount(
                "Dakota","9am-5pm", ServiceType.BARBER, barberServicesOffered));
    }

    void createSpyStylistServicer() {
        spyStylistServicer = spy(new ListingTest.TestServicerAccount(
                "Juliet","11am-2pm", ServiceType.STYLIST, stylistServicesOffered));
    }

    void createShaveListing() {
        shaveListing = spyBarberServicer.new Listing(shaveService);
    }

    void createDyeListing() {
        dyeListing = spyStylistServicer.new Listing(dyeService);
    }

    void createServiceList() {
        ArrayList<ServicerAccount.Listing> tempList =
                new ArrayList<>(List.of(shaveListing, dyeListing));
        serviceList = new ServiceList(tempList);
    }

    @BeforeEach
    void setUp() {
        createBarberServicesOffered();

        createStylistServicesOffered();

        createShaveService();

        createDyeService();

        createSpyBarberServicer();

        createSpyStylistServicer();

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
    void filterByServiceReturnsCorrectFilteredList() {
        ArrayList<ServicerAccount.Listing> expectedList =
                new ArrayList<>((List.of(dyeListing)));
        assertEquals(expectedList, serviceList.filterByService(ServiceType.STYLIST));
    }

    @Test
    void filterByPrice() {
        assertTrue(true);
    }
}
