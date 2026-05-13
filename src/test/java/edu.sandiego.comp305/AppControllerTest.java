package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppControllerTest {

    private TerminalView buildView(final String... promptResponses) {
        final TerminalView mockView = Mockito.mock(TerminalView.class);
        if (promptResponses.length == 1) {
            Mockito.when(mockView.prompt(Mockito.anyString()))
                    .thenReturn(promptResponses[0]);
        } else {
            final String first = promptResponses[0];
            final String[] rest = new String[promptResponses.length - 1];
            System.arraycopy(promptResponses, 1, rest, 0, rest.length);
            Mockito.when(mockView.prompt(Mockito.anyString()))
                    .thenReturn(first, rest);
        }
        return mockView;
    }

    private ServiceList buildServiceList(
            final ServicerAccount.Listing... listings) {
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final ArrayList<ServicerAccount.Listing> list =
                new ArrayList<>(List.of(listings));
        Mockito.when(mockServiceList.getList()).thenReturn(list);
        for (int i = 0; i < listings.length; i++) {
            Mockito.when(mockServiceList.getListing(i))
                    .thenReturn(listings[i]);
        }
        return mockServiceList;
    }

    private ServicerAccount.Listing buildListing(
            final String providerName,
            final Service service,
            final ServiceType serviceType) {
        final ServicerAccount.Listing mockListing =
                Mockito.mock(ServicerAccount.Listing.class);
        Mockito.when(mockListing.getProviderName())
                .thenReturn(providerName);
        Mockito.when(mockListing.getServiceOffered())
                .thenReturn(service);
        Mockito.when(mockListing.getGeneralServiceType())
                .thenReturn(serviceType);
        return mockListing;
    }

    private Service buildService(final String name, final double price) {
        final Service mockService = Mockito.mock(Service.class);
        Mockito.when(mockService.getName()).thenReturn(name);
        Mockito.when(mockService.getPrice()).thenReturn(price);
        return mockService;
    }

    @Test
    void testRunRoutesToCustomerFlow() {
        final Service mockService = buildService("Shave", 20.0);
        final ServicerAccount.Listing mockListing =
                buildListing("Jake", mockService, ServiceType.BARBER);
        final ServiceList mockServiceList = buildServiceList(mockListing);
        final TerminalView mockView = buildView(
                "Jake", "jake123", "SafePass1!",
                "C", "123 Main St", "4");
        final AppController controller =
                new AppController(mockServiceList, mockView);
        controller.run();
        Mockito.verify(mockView, atLeastOnce()).prompt(Mockito.anyString());
    }

    @Test
    void testRunRoutesToServicerFlow() {
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        Mockito.when(mockServiceList.getList()).thenReturn(new ArrayList<>());
        final TerminalView mockView = buildView(
                "Juliet", "juliet123", "SafePass1!",
                "S", "BARBER", "9am-5pm",
                "Shave", "20.0", "done", "4");
        final AppController controller =
                new AppController(mockServiceList, mockView);
        controller.run();
        Mockito.verify(mockView, atLeastOnce()).prompt(Mockito.anyString());
    }

    @Test
    void promptUntilValidFirstAttemptValid() {
        final TerminalView mockView = mock(TerminalView.class);

        final ServiceList mockServiceList = mock(ServiceList.class);
        final AppController controller =
                new AppController(mockServiceList, mockView);

        when(mockView.prompt("Enter name:")).thenReturn("Juliet");
        final Consumer<String> noOpSetter = value -> {};

        final String result = controller.promptUntilValid(
                "Enter name:", noOpSetter);

        assertEquals("Juliet", result);
    }

    @Test
    void promptUntilValidSecondAttemptValid() {
        final TerminalView mockView = mock(TerminalView.class);
        final ServiceList mockServiceList = mock(ServiceList.class);
        final AppController controller =
                new AppController(mockServiceList, mockView);

        when(mockView.prompt("Enter name:"))
                .thenReturn("")     // first attempt fails
                .thenReturn("Juliet"); // second attempt succeeds

        final Consumer<String> strictSetter = value -> {
            if (value.isEmpty()) {
                throw new IllegalArgumentException("Name cannot be empty");
            }
        };

        final String result = controller.promptUntilValid(
                "Enter name:", strictSetter);

        assertEquals("Juliet", result);
        verify(mockView, times(2)).prompt("Enter name:");
    }

    @Test
    void promptUntilValidTestErrorDisplay() {
        final TerminalView mockView = mock(TerminalView.class);
        final ServiceList mockServiceList = mock(ServiceList.class);
        final AppController controller =
                new AppController(mockServiceList, mockView);

        when(mockView.prompt("Enter name:"))
                .thenReturn("")
                .thenReturn("Juliet");

        final Consumer<String> strictSetter = value -> {
            if (value.isEmpty()) {
                throw new IllegalArgumentException("Name cannot be empty");
            }
        };

        controller.promptUntilValid("Enter name:", strictSetter);

        verify(mockView).display("Name cannot be empty");
    }

    @Test
    void promptUntilValidMultipleReprompts() {
        final TerminalView mockView = mock(TerminalView.class);
        final ServiceList mockServiceList = mock(ServiceList.class);
        final AppController controller =
                new AppController(mockServiceList, mockView);

        when(mockView.prompt("Enter name:"))
                .thenReturn("")
                .thenReturn("")
                .thenReturn("Juliet");

        final Consumer<String> strictSetter = value -> {
            if (value.isEmpty()) {
                throw new IllegalArgumentException("Name cannot be empty");
            }
        };

        final String result = controller.promptUntilValid(
                "Enter name:", strictSetter);

        assertEquals("Juliet", result);
        verify(mockView, times(3)).prompt("Enter name:");
        verify(mockView, times(2)).display("Name cannot be empty");
    }

    @Test
    void testRunInvalidAccountTypeRePrompts() {
        final Service mockService = buildService("Shave", 20.0);
        final ServicerAccount.Listing mockListing =
                buildListing("Jake", mockService, ServiceType.BARBER);
        final ServiceList mockServiceList = buildServiceList(mockListing);
        final TerminalView mockView = buildView(
                "Jake", "jake123", "SafePass1!",
                "X", "C", "123 Main St", "4");
        final AppController controller =
                new AppController(mockServiceList, mockView);
        controller.run();
        Mockito.verify(mockView, atLeastOnce()).display(Mockito.anyString());
    }

    @Test
    void testHandleSearchFiltersServiceList() {
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        Mockito.when(mockServiceList.filterByService(ServiceType.BARBER))
                .thenReturn(new ArrayList<>());
        final TerminalView mockView = Mockito.mock(TerminalView.class);
        final AppController controller =
                new AppController(mockServiceList, mockView);
        controller.handleSearch(ServiceType.BARBER, 50.0);
        Mockito.verify(mockServiceList).filterByService(ServiceType.BARBER);
    }

    @Test
    void testHandleSearchDisplaysResults() {
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final List<ServicerAccount.Listing> results = new ArrayList<>();
        Mockito.when(mockServiceList.filterByService(ServiceType.NAIL_TECH))
                .thenReturn(results);
        final TerminalView mockView = Mockito.mock(TerminalView.class);
        final AppController controller =
                new AppController(mockServiceList, mockView);
        controller.handleSearch(ServiceType.NAIL_TECH, 100.0);
        Mockito.verify(mockView).displayListings(results);
    }

    @Test
    void testHandlePaymentSuccessWithCash() {
        final Service mockService = buildService("Shave", 20.0);
        final ServicerAccount.Listing mockListing =
                buildListing("Jake", mockService, ServiceType.BARBER);
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        Mockito.when(mockServiceList.getList()).thenReturn(new ArrayList<>());
        final Customer mockCustomer = Mockito.mock(Customer.class);
        Mockito.when(mockCustomer.getSelectedListing()).thenReturn(mockListing);
        Mockito.when(mockCustomer.pay(Mockito.anyDouble(),
                Mockito.any(PaymentMethod.class),
                Mockito.any(Service.class))).thenReturn(true);
        final TerminalView mockView = buildView("CASH", "20.0", "4");
        final AppController controller =
                new AppController(mockServiceList, mockView);
        controller.handlePayment(mockCustomer);
        Mockito.verify(mockCustomer).pay(Mockito.anyDouble(),
                Mockito.any(PaymentMethod.class),
                Mockito.any(Service.class));
    }

    @Test
    void testHandlePaymentSuccessWithCredit() {
        final Service mockService = buildService("Shave", 20.0);
        final ServicerAccount.Listing mockListing =
                buildListing("Jake", mockService, ServiceType.BARBER);
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        Mockito.when(mockServiceList.getList()).thenReturn(new ArrayList<>());
        final Customer mockCustomer = Mockito.mock(Customer.class);
        Mockito.when(mockCustomer.getSelectedListing()).thenReturn(mockListing);
        Mockito.when(mockCustomer.pay(Mockito.anyDouble(),
                Mockito.any(PaymentMethod.class),
                Mockito.any(Service.class))).thenReturn(true);
        final TerminalView mockView = buildView(
                "CREDIT", "1111222233334444", "20.0", "4");
        final AppController controller =
                new AppController(mockServiceList, mockView);
        controller.handlePayment(mockCustomer);
        Mockito.verify(mockCustomer).pay(Mockito.anyDouble(),
                Mockito.any(PaymentMethod.class),
                Mockito.any(Service.class));
    }

    @Test
    void testHandlePaymentSuccessWithVenmo() {
        final Service mockService = buildService("Shave", 20.0);
        final ServicerAccount.Listing mockListing =
                buildListing("Jake", mockService, ServiceType.BARBER);
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        Mockito.when(mockServiceList.getList()).thenReturn(new ArrayList<>());
        final Customer mockCustomer = Mockito.mock(Customer.class);
        Mockito.when(mockCustomer.getSelectedListing()).thenReturn(mockListing);
        Mockito.when(mockCustomer.pay(Mockito.anyDouble(),
                Mockito.any(PaymentMethod.class),
                Mockito.any(Service.class))).thenReturn(true);
        final TerminalView mockView = buildView("VENMO", "@jake", "20.0", "4");
        final AppController controller =
                new AppController(mockServiceList, mockView);
        controller.handlePayment(mockCustomer);
        Mockito.verify(mockCustomer).pay(Mockito.anyDouble(),
                Mockito.any(PaymentMethod.class),
                Mockito.any(Service.class));
    }

    @Test
    void testHandlePaymentRePromptsOnInsufficientAmount() {
        final Service mockService = buildService("Shave", 20.0);
        final ServicerAccount.Listing mockListing =
                buildListing("Jake", mockService, ServiceType.BARBER);
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        Mockito.when(mockServiceList.getList()).thenReturn(new ArrayList<>());
        final Customer mockCustomer = Mockito.mock(Customer.class);
        Mockito.when(mockCustomer.getSelectedListing()).thenReturn(mockListing);
        Mockito.when(mockCustomer.pay(Mockito.anyDouble(),
                        Mockito.any(PaymentMethod.class),
                        Mockito.any(Service.class)))
                .thenReturn(false)
                .thenReturn(true);
        final TerminalView mockView = buildView(
                "CASH", "5.0", "20.0", "4");
        final AppController controller =
                new AppController(mockServiceList, mockView);
        controller.handlePayment(mockCustomer);
        Mockito.verify(mockCustomer, times(2)).pay(Mockito.anyDouble(),
                Mockito.any(PaymentMethod.class),
                Mockito.any(Service.class));
    }

    @Test
    void testBuildPaymentMethodReturnsCashPayment() {
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final TerminalView mockView = buildView("CASH");
        final AppController controller =
                new AppController(mockServiceList, mockView);
        final PaymentMethod result = controller.buildPaymentMethod(20.0);
        assertInstanceOf(CashPayment.class, result);
    }

    @Test
    void testBuildPaymentMethodReturnsCreditPayment() {
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final TerminalView mockView = buildView("CREDIT", "1111222233334444");
        final AppController controller =
                new AppController(mockServiceList, mockView);
        final PaymentMethod result = controller.buildPaymentMethod(20.0);
        assertInstanceOf(CreditCardPayment.class, result);
    }

    @Test
    void testBuildPaymentMethodReturnsVenmoPayment() {
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final TerminalView mockView = buildView("VENMO", "@jake");
        final AppController controller =
                new AppController(mockServiceList, mockView);
        final PaymentMethod result = controller.buildPaymentMethod(20.0);
        assertInstanceOf(VenmoPayment.class, result);
    }

    @Test
    void testBuildPaymentMethodRePromptsOnInvalidType() {
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final TerminalView mockView = buildView("DEBIT", "CASH");
        final AppController controller =
                new AppController(mockServiceList, mockView);
        final PaymentMethod result = controller.buildPaymentMethod(20.0);
        assertInstanceOf(CashPayment.class, result);
    }

    @Test
    void testBuildCreditPaymentReturnsValidPayment() {
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final TerminalView mockView = buildView("1111222233334444");
        final AppController controller =
                new AppController(mockServiceList, mockView);
        final PaymentMethod result = controller.buildCreditPayment(20.0);
        assertInstanceOf(CreditCardPayment.class, result);
    }

    @Test
    void testBuildCreditPaymentRePromptsOnInvalidCard() {
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final TerminalView mockView = buildView("1111", "1111222233334444");
        final AppController controller =
                new AppController(mockServiceList, mockView);
        final PaymentMethod result = controller.buildCreditPayment(20.0);
        assertInstanceOf(CreditCardPayment.class, result);
    }

    @Test
    void testBuildVenmoPaymentReturnsValidPayment() {
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final TerminalView mockView = buildView("@jake");
        final AppController controller =
                new AppController(mockServiceList, mockView);
        final PaymentMethod result = controller.buildVenmoPayment(20.0);
        assertInstanceOf(VenmoPayment.class, result);
    }

    @Test
    void testBuildVenmoPaymentRePromptsOnMissingAtSign() {
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final TerminalView mockView = buildView("jake", "@jake");
        final AppController controller =
                new AppController(mockServiceList, mockView);
        final PaymentMethod result = controller.buildVenmoPayment(20.0);
        assertInstanceOf(VenmoPayment.class, result);
    }

    @Test
    void testPostListingAddsToServiceList() {
        final Service mockService = buildService("Shave", 20.0);
        final ArrayList<Service> services =
                new ArrayList<>(List.of(mockService));
        final ServicerAccount mockServicer =
                Mockito.mock(ServicerAccount.class);
        Mockito.when(mockServicer.getServicesOffered()).thenReturn(services);
        Mockito.when(mockServicer.getName()).thenReturn("Jake");
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final TerminalView mockView = buildView("1");
        final AppController controller =
                new AppController(mockServiceList, mockView);
        controller.postListing(mockServicer);
        Mockito.verify(mockServiceList).addListing(
                Mockito.any(ServicerAccount.Listing.class));
    }

    @Test
    void testPostListingInvalidSelectionDoesNotAdd() {
        final Service mockService = buildService("Shave", 20.0);
        final ArrayList<Service> services =
                new ArrayList<>(List.of(mockService));
        final ServicerAccount mockServicer =
                Mockito.mock(ServicerAccount.class);
        Mockito.when(mockServicer.getServicesOffered()).thenReturn(services);
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final TerminalView mockView = buildView("99");
        final AppController controller =
                new AppController(mockServiceList, mockView);
        controller.postListing(mockServicer);
        Mockito.verify(mockServiceList, never()).addListing(Mockito.any());
    }

    @Test
    void testViewListingsDisplaysMatchingListings() {
        final ServicerAccount.Listing mockListing =
                Mockito.mock(ServicerAccount.Listing.class);
        Mockito.when(mockListing.getProviderName()).thenReturn("Jake");
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        Mockito.when(mockServiceList.getList())
                .thenReturn(new ArrayList<>(List.of(mockListing)));
        final TerminalView mockView = Mockito.mock(TerminalView.class);
        final ServicerAccount mockServicer =
                Mockito.mock(ServicerAccount.class);
        Mockito.when(mockServicer.getName()).thenReturn("Jake");
        final AppController controller =
                new AppController(mockServiceList, mockView);
        controller.viewListings(mockServicer);
        Mockito.verify(mockView).displayListings(Mockito.any());
    }

    @Test
    void testViewListingsDisplaysMessageWhenEmpty() {
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        Mockito.when(mockServiceList.getList()).thenReturn(new ArrayList<>());
        final TerminalView mockView = Mockito.mock(TerminalView.class);
        final ServicerAccount mockServicer =
                Mockito.mock(ServicerAccount.class);
        Mockito.when(mockServicer.getName()).thenReturn("Jake");
        final AppController controller =
                new AppController(mockServiceList, mockView);
        controller.viewListings(mockServicer);
        Mockito.verify(mockView).display("You have no active listings.");
    }

    @Test
    void testUpdateAvailabilityUpdatesServicer() {
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final TerminalView mockView = buildView("9am-5pm");
        final ServicerAccount mockServicer =
                Mockito.mock(ServicerAccount.class);
        Mockito.when(mockServicer.getName()).thenReturn("Jake");
        final AppController controller =
                new AppController(mockServiceList, mockView);
        controller.updateAvailability(mockServicer);
        Mockito.verify(mockServicer).setAvailability("9am-5pm");
    }

    @Test
    void testRunServicerMenuExitsOnFour() {
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final TerminalView mockView = buildView("4");
        final ServicerAccount mockServicer =
                Mockito.mock(ServicerAccount.class);
        Mockito.when(mockServicer.getName()).thenReturn("Jake");
        final AppController controller =
                new AppController(mockServiceList, mockView);
        controller.runServicerMenu(mockServicer);
        Mockito.verify(mockView).display("Goodbye, Jake!");
    }

    @Test
    void testRunServicerMenuInvalidOptionRePrompts() {
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final TerminalView mockView = buildView("9", "4");
        final ServicerAccount mockServicer =
                Mockito.mock(ServicerAccount.class);
        Mockito.when(mockServicer.getName()).thenReturn("Jake");
        final AppController controller =
                new AppController(mockServiceList, mockView);
        controller.runServicerMenu(mockServicer);
        Mockito.verify(mockView).display("Invalid option. Please try again.");
    }

    @Test
    void testHandlePostListingAddsService() {
        final ServicerAccount mockServicer =
                Mockito.mock(ServicerAccount.class);
        Mockito.when(mockServicer.getServicesOffered())
                .thenReturn(new ArrayList<>());
        final ServiceList mockServiceList = Mockito.mock(ServiceList.class);
        final TerminalView mockView = buildView("Haircut", "20.0");
        final AppController controller =
                new AppController(mockServiceList, mockView);
        controller.handlePostListing(mockServicer);
        Mockito.verify(mockServicer).setServicesOffered(Mockito.any());
    }
}
