package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Scanner;

class AppControllerTest {

    @Test
    void handleServiceSelection() {
        final Customer mockCustomer = Mockito.mock(Customer.class);
        final ServicerAccount mockServicer =
                Mockito.mock(ServicerAccount.class);
        final CustomerView mockCustomerView = Mockito.mock(CustomerView.class);
        final ServicerView mockServicerView = Mockito.mock(ServicerView.class);
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final Scanner scanner = new Scanner("test input\n");
        final AppController controller = new AppController(mockCustomer,
                mockServicer, mockCustomerView,
                mockServicerView, mockServiceList, scanner);
        final Service mockService = Mockito.mock(Service.class);
        controller.handleServiceSelection(mockService, mockServicer);
        Mockito.verify(mockServicer).update(Mockito.any(Customer.class),
                Mockito.any(ServicerAccount.Listing.class));
    }

    @Test
    void handleSearch() {
        final Customer mockCustomer = Mockito.mock(Customer.class);
        final ServicerAccount mockServicer =
                Mockito.mock(ServicerAccount.class);
        final CustomerView mockCustomerView = Mockito.mock(CustomerView.class);
        final ServicerView mockServicerView = Mockito.mock(ServicerView.class);
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final Scanner scanner = new Scanner("test input\n");
        final AppController controller = new AppController(mockCustomer,
                mockServicer, mockCustomerView,
                mockServicerView, mockServiceList, scanner);
        final Service mockService = Mockito.mock(Service.class);
        Mockito.when(mockService.getName()).thenReturn("Haircut");
        controller.handleSearch(mockService, 50.0);
        Mockito.verify(mockServiceList).filterByService("Haircut");
    }

    @Test
    void testRun() {
        final Customer mockCustomer = Mockito.mock(Customer.class);
        final ServicerAccount mockServicer =
                Mockito.mock(ServicerAccount.class);
        final CustomerView mockCustomerView = Mockito.mock(CustomerView.class);
        final ServicerView mockServicerView = Mockito.mock(ServicerView.class);
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final ServicerAccount.Listing mockListing =
                Mockito.mock(ServicerAccount.Listing.class);
        final Service mockService = Mockito.mock(Service.class);
        final Scanner scanner = new Scanner(
                "Dakota\nuser123\nPass1!\nC\n123 st\n0\n50.0\nVENMO" +
                        "\nmyvenmo\n");
        final AppController controller = new AppController(mockCustomer,
                mockServicer, mockCustomerView,
                mockServicerView, mockServiceList, scanner);
        Mockito.when(mockServiceList.getList())
                .thenReturn(new java.util.ArrayList<>());
        Mockito.when(mockCustomer.getSelectedListing())
                .thenReturn(mockListing);
        Mockito.when(mockListing.getServiceOffered())
                .thenReturn(mockService);
        controller.run();
        Mockito.verify(mockCustomer).selectListing(mockServiceList, 0);
        Mockito.verify(mockCustomer).pay(Mockito.anyDouble(),
                Mockito.any(PaymentMethod.class),
                Mockito.any(Service.class));
    }

    @Test
    void testRunServicer() {
        final Customer mockCustomer = Mockito.mock(Customer.class);
        final ServicerAccount mockServicer =
                Mockito.mock(ServicerAccount.class);
        final CustomerView mockCustomerView = Mockito.mock(CustomerView.class);
        final ServicerView mockServicerView = Mockito.mock(ServicerView.class);
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final Scanner scanner = new Scanner("Dakota\nuser123\nPass1!\nS" +
                "\nHaircut\n20.0\n");
        final AppController controller = new AppController(mockCustomer,
                mockServicer, mockCustomerView,
                mockServicerView, mockServiceList, scanner);
        controller.run();
        Mockito.verify(mockServicerView)
                .showOfferedServices(Mockito.any());
    }

    @Test
    void testHandlePayment() {
        final Customer mockCustomer = Mockito.mock(Customer.class);
        final ServicerAccount mockServicer =
                Mockito.mock(ServicerAccount.class);
        final CustomerView mockCustomerView = Mockito.mock(CustomerView.class);
        final ServicerView mockServicerView = Mockito.mock(ServicerView.class);
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final ServicerAccount.Listing mockListing =
                Mockito.mock(ServicerAccount.Listing.class);
        final Service mockService = Mockito.mock(Service.class);
        final Scanner scanner = new Scanner("50.0\nCASH\n");
        final AppController controller = new AppController(mockCustomer,
                mockServicer, mockCustomerView,
                mockServicerView, mockServiceList, scanner);
        Mockito.when(mockCustomer.getSelectedListing())
                .thenReturn(mockListing);
        Mockito.when(mockListing.getServiceOffered())
                .thenReturn(mockService);
        controller.handlePayment();
        Mockito.verify(mockCustomer).pay(Mockito.anyDouble(),
                Mockito.any(PaymentMethod.class),
                Mockito.any(Service.class));
    }

    @Test
    void testHandlePostListing() {
        final Customer mockCustomer = Mockito.mock(Customer.class);
        final ServicerAccount mockServicer =
                Mockito.mock(ServicerAccount.class);
        final CustomerView mockCustomerView = Mockito.mock(CustomerView.class);
        final ServicerView mockServicerView = Mockito.mock(ServicerView.class);
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final Scanner scanner = new Scanner("Haircut\n20.0\n");
        final AppController controller = new AppController(mockCustomer,
                mockServicer, mockCustomerView,
                mockServicerView, mockServiceList, scanner);
        controller.handlePostListing(mockServicer);
        Mockito.verify(mockServicer).setServicesOffered(Mockito.any());
    }

    @Test
    void testHandleScheduleAppointment() {
        final Customer mockCustomer = Mockito.mock(Customer.class);
        final ServicerAccount mockServicer =
                Mockito.mock(ServicerAccount.class);
        final CustomerView mockCustomerView = Mockito.mock(CustomerView.class);
        final ServicerView mockServicerView = Mockito.mock(ServicerView.class);
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final Scanner scanner = new Scanner("0\n");
        final AppController controller = new AppController(mockCustomer,
                mockServicer, mockCustomerView,
                mockServicerView, mockServiceList, scanner);
        Mockito.when(mockServiceList.getList())
                .thenReturn(new java.util.ArrayList<>());
        controller.handleScheduleAppointment(mockServiceList);
        Mockito.verify(mockCustomer).selectListing(mockServiceList, 0);
    }

    @Test
    void testHandleCustomerFlow() {
        final Customer mockCustomer = Mockito.mock(Customer.class);
        final ServicerAccount mockServicer =
                Mockito.mock(ServicerAccount.class);
        final CustomerView mockCustomerView = Mockito.mock(CustomerView.class);
        final ServicerView mockServicerView = Mockito.mock(ServicerView.class);
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final ServicerAccount.Listing mockListing =
                Mockito.mock(ServicerAccount.Listing.class);
        final Service mockService = Mockito.mock(Service.class);
        final Scanner scanner = new Scanner("123 st\n0\n50.0\nCASH\n");
        final AppController controller = new AppController(mockCustomer,
                mockServicer, mockCustomerView,
                mockServicerView, mockServiceList, scanner);
        Mockito.when(mockServiceList.getList())
                .thenReturn(new java.util.ArrayList<>());
        Mockito.when(mockCustomer.getSelectedListing())
                .thenReturn(mockListing);
        Mockito.when(mockListing.getServiceOffered())
                .thenReturn(mockService);
        controller.handleCustomerFlow();
        Mockito.verify(mockCustomer).selectListing(mockServiceList, 0);
    }

    @Test
    void testHandleServicerFlow() {
        final Customer mockCustomer = Mockito.mock(Customer.class);
        final ServicerAccount mockServicer =
                Mockito.mock(ServicerAccount.class);
        final CustomerView mockCustomerView = Mockito.mock(CustomerView.class);
        final ServicerView mockServicerView = Mockito.mock(ServicerView.class);
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final Scanner scanner = new Scanner("Haircut\n20.0\n");
        final AppController controller = new AppController(mockCustomer,
                mockServicer, mockCustomerView,
                mockServicerView, mockServiceList, scanner);
        Mockito.when(mockServicer.getServicesOffered())
                .thenReturn(new java.util.ArrayList<>());
        controller.handleServicerFlow();
        Mockito.verify(mockServicerView)
                .showOfferedServices(Mockito.any());
    }
}
