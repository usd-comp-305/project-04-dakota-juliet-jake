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
            "Barber", servicesOffered);

    @Test
    public void takeCallSetAvailabilityToFalse() {
        servicer.takeCall(mockCustomer, mockService);
        assertFalse(servicer.getIsAvailable());
    }

    @Test
    public void takeCallAddToSchedule(){
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
        when(servicer.getIsAvailable()).thenReturn(false);
        assertFalse(servicer.getIsAvailable());
        servicer.postService(mockService);
        assertTrue(servicer.getIsAvailable());
    }

    @Test
    public void postService_listingHasCorrectService() {
        when(mockService.getName()).thenReturn("Buzz");
        servicer.postService(mockService);
        assertEquals("Buzz", servicer.getListings().getFirst().getServiceOffered().getName());
    }

    static class TestServicer extends ServicerAccount {
        public TestServicer(final String name,
                            final String availability,
                            final String generalServiceType,
                            final ArrayList<Service> servicesOffered){
            super(name, availability, generalServiceType, servicesOffered);
        }
    }
}
