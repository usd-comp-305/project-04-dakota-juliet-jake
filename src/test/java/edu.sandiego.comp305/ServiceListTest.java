package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

class ServiceListTest {
    private ServiceList serviceList;

    private ListingTest.TestServicerAccount spyBarberServicer;

    private ListingTest.TestServicerAccount spyStylistServicer;

    private ServicerAccount.Listing shaveListing;

    private ServicerAccount.Listing dyeListing;

    private Service shaveService;

    private Service dyeService;

    void createShaveService() {
        shaveService = new Service("Shave", 20.0);
    }

    void createDyeService() {
        dyeService = new Service("Full Dye", 150.0);
    }

    void createSpyBarberServicer() {
        ArrayList<Service> barberServicesOffered = new ArrayList<>(List.of(
                shaveService,
                new Service("Shear", 25.0)));
        spyBarberServicer = spy(new ListingTest.TestServicerAccount(
                "Dakota","9am-5pm", ServiceType.BARBER, barberServicesOffered));
    }

    void createSpyStylistServicer() {
        ArrayList<Service> stylistServicesOffered = new ArrayList<>(List.of(
                dyeService,
                new Service("Highlights", 120.0)));
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
        createShaveService();

        createDyeService();

        createSpyBarberServicer();

        createSpyStylistServicer();

        createShaveListing();

        createDyeListing();

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
    void filterByUnavailableServiceReturnsEmptyList() {
        ArrayList<ServicerAccount.Listing> expectedList =
                new ArrayList<>((List.of()));
        assertEquals(expectedList, serviceList.filterByService(ServiceType.NAIL_TECH));
    }

    @Test
    void filterByServiceOnEmptyListReturnsEmptyList() {
        ServiceList emptyServiceList = new ServiceList(new ArrayList<>());
        ArrayList<ServicerAccount.Listing> expectedList =
                new ArrayList<>((List.of()));
        assertEquals(expectedList, emptyServiceList.filterByService(ServiceType.BARBER));
    }

    @Test
    void filterByPriceReturnsCorrectFilteredList() {
        ArrayList<ServicerAccount.Listing> expectedList =
                new ArrayList<>((List.of(shaveListing)));
        assertEquals(expectedList, serviceList.filterByPrice(50.0));
    }

    @Test
    void filterByLowPriceReturnsCorrectFilteredList() {
        ArrayList<ServicerAccount.Listing> expectedList =
                new ArrayList<>();
        assertEquals(expectedList, serviceList.filterByPrice(10.0));
    }

    @Test
    void filterByEncompassingPriceReturnsOriginalList() {
        assertEquals(serviceList.getList(), serviceList.filterByPrice(200.0));
    }

    @Test
    void filterByExactPriceReturnsCorrectFilteredList() {
        ArrayList<ServicerAccount.Listing> expectedList =
                new ArrayList<>((List.of(shaveListing)));
        assertEquals(expectedList, serviceList.filterByPrice(20.0));
    }

    @Test
    void filterByPriceOnEmptyListReturnsEmptyList() {
        ServiceList emptyServiceList = new ServiceList(new ArrayList<>());
        ArrayList<ServicerAccount.Listing> expectedList =
                new ArrayList<>((List.of()));
        assertEquals(expectedList, emptyServiceList.filterByPrice(100.0));
    }

}
