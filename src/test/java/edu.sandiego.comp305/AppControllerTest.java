package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AppControllerTest {

    @Test
    void handleServiceSelection() {
        final Customer mockCustomer = Mockito.mock(Customer.class);
        final ServicerAccount mockServicer = Mockito.mock(ServicerAccount.class);
        final CustomerView mockCustomerView = Mockito.mock(CustomerView.class);
        final ServicerView mockServicerView = Mockito.mock(ServicerView.class);
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final AppController controller = new AppController(mockCustomer,
                mockServicer, mockCustomerView, mockServicerView, mockServiceList);
        final Service mockService = Mockito.mock(Service.class);
        controller.handleServiceSelection(mockService, mockServicer);
        Mockito.verify(mockServicer).update(Mockito.any(Customer.class),
                Mockito.any(ServicerAccount.Listing.class));
    }

    @Test
    void handleSearch() {
    }

    @Test
    void handlePayment() {
    }

    @Test
    void handlePostListing() {
    }

    @Test
    void handleJoinQueue() {
    }
}
