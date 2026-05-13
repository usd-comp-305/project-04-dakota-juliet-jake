package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Scanner;

class AppControllerTest {

    @Test
    void handleSearch() {
        final CustomerView mockCustomerView = Mockito.mock(CustomerView.class);
        final ServicerView mockServicerView = Mockito.mock(ServicerView.class);
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final TerminalView mockTerminalView = Mockito.mock(TerminalView.class);
        final AppController controller = new AppController(mockServiceList, mockTerminalView);
        controller.handleSearch(ServiceType.BARBER, 50.0);
        Mockito.verify(mockServiceList).filterByService(ServiceType.BARBER);
    }

    @Test
    void testRun() {
        final Customer mockCustomer = Mockito.mock(Customer.class);
        final CustomerView mockCustomerView = Mockito.mock(CustomerView.class);
        final ServicerView mockServicerView = Mockito.mock(ServicerView.class);
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final ServicerAccount.Listing mockListing =
                Mockito.mock(ServicerAccount.Listing.class);
        final Service mockService = Mockito.mock(Service.class);
        final TerminalView mockTerminalView = Mockito.mock(TerminalView.class);
        final AppController controller = new AppController(mockServiceList, mockTerminalView);
        Mockito.when(mockServiceList.getList())
                .thenReturn(new java.util.ArrayList<>());
        Mockito.when(mockCustomer.getSelectedListing())
                .thenReturn(mockListing);
        Mockito.when(mockListing.getServiceOffered())
                .thenReturn(mockService);
        Mockito.when(mockServiceList.getListing(0)).thenReturn(mockListing);
        Mockito.when(mockCustomer.pay(Mockito.anyDouble(),
                Mockito.any(PaymentMethod.class),
                Mockito.any(Service.class))).thenReturn(true);
        controller.run();
        Mockito.verify(mockCustomer).selectListing(mockServiceList, 0);
        Mockito.verify(mockCustomer).pay(Mockito.anyDouble(),
                Mockito.any(PaymentMethod.class),
                Mockito.any(Service.class));
    }

    @Test
    void testRunServicer() {
        final CustomerView mockCustomerView = Mockito.mock(CustomerView.class);
        final ServicerView mockServicerView = Mockito.mock(ServicerView.class);
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final TerminalView mockTerminalView = Mockito.mock(TerminalView.class);
        final AppController controller = new AppController(mockServiceList, mockTerminalView);
        controller.run();
        Mockito.verify(mockServicerView)
                .showOfferedServices(Mockito.any());
    }

    @Test
    void testHandlePayment() {
        final Customer mockCustomer = Mockito.mock(Customer.class);
        final CustomerView mockCustomerView = Mockito.mock(CustomerView.class);
        final ServicerView mockServicerView = Mockito.mock(ServicerView.class);
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final ServicerAccount.Listing mockListing =
                Mockito.mock(ServicerAccount.Listing.class);
        final Service mockService = Mockito.mock(Service.class);
        final TerminalView mockTerminalView = Mockito.mock(TerminalView.class);
        final AppController controller = new AppController(mockServiceList, mockTerminalView);
        Mockito.when(mockCustomer.getSelectedListing())
                .thenReturn(mockListing);
        Mockito.when(mockListing.getServiceOffered())
                .thenReturn(mockService);
        Mockito.when(mockCustomer.pay(Mockito.anyDouble(),
                Mockito.any(PaymentMethod.class),
                Mockito.any(Service.class))).thenReturn(true);
        controller.handlePayment(mockCustomer);
        Mockito.verify(mockCustomer).pay(Mockito.anyDouble(),
                Mockito.any(PaymentMethod.class),
                Mockito.any(Service.class));
    }

    @Test
    void testHandlePostListing() {
        final ServicerAccount mockServicer =
                Mockito.mock(ServicerAccount.class);
        final CustomerView mockCustomerView = Mockito.mock(CustomerView.class);
        final ServicerView mockServicerView = Mockito.mock(ServicerView.class);
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final TerminalView mockTerminalView = Mockito.mock(TerminalView.class);
        final AppController controller = new AppController(mockServiceList, mockTerminalView);
        controller.handlePostListing(mockServicer);
        Mockito.verify(mockServicer).setServicesOffered(Mockito.any());
    }

    @Test
    void testHandleCustomerFlow() {
        final Customer mockCustomer = Mockito.mock(Customer.class);
        final CustomerView mockCustomerView = Mockito.mock(CustomerView.class);
        final ServicerView mockServicerView = Mockito.mock(ServicerView.class);
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final ServicerAccount.Listing mockListing =
                Mockito.mock(ServicerAccount.Listing.class);
        final Service mockService = Mockito.mock(Service.class);
        final TerminalView mockTerminalView = Mockito.mock(TerminalView.class);
        final AppController controller = new AppController(mockServiceList, mockTerminalView);
        Mockito.when(mockServiceList.getList())
                .thenReturn(new java.util.ArrayList<>());
        Mockito.when(mockCustomer.getSelectedListing())
                .thenReturn(mockListing);
        Mockito.when(mockListing.getServiceOffered())
                .thenReturn(mockService);
        Mockito.when(mockServiceList.getListing(0)).thenReturn(mockListing);
        Mockito.when(mockCustomer.pay(Mockito.anyDouble(),
                Mockito.any(PaymentMethod.class),
                Mockito.any(Service.class))).thenReturn(true);
        controller.handleCustomerFlow();
        Mockito.verify(mockCustomer).selectListing(mockServiceList, 0);
    }

    @Test
    void testHandleServicerFlow() {
        final ServicerAccount mockServicer =
                Mockito.mock(ServicerAccount.class);
        final CustomerView mockCustomerView = Mockito.mock(CustomerView.class);
        final ServicerView mockServicerView = Mockito.mock(ServicerView.class);
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final TerminalView mockTerminalView = Mockito.mock(TerminalView.class);
        final AppController controller = new AppController(mockServiceList, mockTerminalView);
        Mockito.when(mockServicer.getServicesOffered())
                .thenReturn(new java.util.ArrayList<>());
        controller.handleServicerFlow();
        Mockito.verify(mockServicerView)
                .showOfferedServices(Mockito.any());
    }
}
