package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicerAccountTest {

    private Customer mockCustomer = mock(Customer.class);

    private Service mockService = mock(Service.class);

    ArrayList<Service> servicesOffered =
            new ArrayList<Service>(Arrays.asList(mockService));

    private TestServicer servicer = new TestServicer("Juliet",
            "Monday, Wednesday, Friday",
            ServiceType.BARBER, servicesOffered);

    @Test
    public void takeCallSetAvailabilityToFalse() {
        servicer.takeCall(mockCustomer, mockService);
        assertFalse(servicer.getIsAvailable());
    }

    @Test
    public void takeCallAddToSchedule(){
        servicer.takeCall(mockCustomer, mockService);
        when(mockService.getName()).thenReturn("Buzz");
        assertTrue(!servicer.getSchedule().isEmpty());
    }

    @Test
    public void takeCallScheduleHasCustomer(){
        servicer.takeCall(mockCustomer, mockService);
        assertTrue(servicer.getSchedule().containsKey(mockCustomer));
    }

    @Test
    public void takeCallScheduleHasCorrectService(){
        servicer.takeCall(mockCustomer, mockService);
        when(mockService.getName()).thenReturn("Buzz");
        assertEquals("Buzz",
                servicer.getSchedule().get(mockCustomer).getName());
    }

    @Test
    public void postServiceAddsToListings(){
        servicer.postService(mockService);
        assertTrue(!servicer.getListings().isEmpty());
    }

    @Test
    public void postServiceSetsIsAvailableToTrue() {
        //use takeCall method to set availability to false
        servicer.takeCall(mockCustomer, mockService);
        assertFalse(servicer.getIsAvailable());
        servicer.postService(mockService);
        assertTrue(servicer.getIsAvailable());
    }

    @Test
    public void postServiceListingHasCorrectService() {
        when(mockService.getName()).thenReturn("Buzz");
        servicer.postService(mockService);
        assertEquals("Buzz",
                servicer.getListings().getFirst().
                        getServiceOffered().getName());
    }

    @Test
    public void cancelCallNoCustomerThrows(){
        assertThrows(UnsupportedOperationException.class,
                () -> servicer.cancelCall());
    }

    @Test
    public void cancelCallWrongCustomerThrows(){
        final Customer newCustomer = mock(Customer.class);
        when(newCustomer.getName()).thenReturn("Jake");

        when(mockCustomer.getName()).thenReturn("Juliet");
        servicer.takeCall(mockCustomer, mockService);
        assertThrows(IllegalStateException.class,
                () -> servicer.cancelCall(newCustomer));
    }

    @Test
    public void cancelCallResetsAvailability(){
        servicer.takeCall(mockCustomer, mockService);
        servicer.cancelCall(mockCustomer);
        assertTrue(servicer.getIsAvailable());
    }

    @Test
    public void cancelCallRemovesCorrectCustomer(){
        servicer.takeCall(mockCustomer, mockService);
        servicer.cancelCall(mockCustomer);
        assertFalse(servicer.getSchedule().containsKey(mockCustomer));

    }


    static class TestServicer extends ServicerAccount {
        public TestServicer(final String name,
                            final String availability,
                            final ServiceType generalServiceType,
                            final ArrayList<Service> servicesOffered){
            super(name, availability, generalServiceType, servicesOffered);
        }

        @Override
        public String performService(final Customer customer) {
            return "";
        }
    }
}
