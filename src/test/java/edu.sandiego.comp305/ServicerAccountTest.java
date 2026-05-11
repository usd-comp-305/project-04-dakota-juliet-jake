package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicerAccountTest {

    private TestServicer servicer = new TestServicer("Juliet", "9-5");

    private Customer mockCustomer = mock(Customer.class);

    private Service mockService = mock(Service.class);

    @Test
    public void takeCallSetAvailabilityToFalse() {
       servicer.takeCall(mockCustomer, mockService);
       assertFalse(servicer.getIsAvailable());
    }

    @Test
    public void takeCallAddToSchedule(){
        servicer.takeCall(mockCustomer, mockService);
        when(mockService.getName()).thenReturn("Buzz");
        assertEquals("Buzz", servicer.getSchedule().getFirst().getName());
    }

    @Test
    public void takeCallAdjustAvailability(){
        servicer.takeCall(mockCustomer, mockService);
        assertEquals("10-5", servicer.getAvailability());
    }

    static class TestServicer extends ServicerAccount {
        public TestServicer(String name, String availability){
            super(name, availability);
        }
    }
}
