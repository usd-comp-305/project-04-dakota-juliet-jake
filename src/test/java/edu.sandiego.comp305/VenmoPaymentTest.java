package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VenmoPaymentTest {

    @Test
    void processPaymentReturnsTrueForValidAmount() {
        final VenmoPayment payment = new VenmoPayment("@jake");
        assertTrue(payment.processPayment(35.0));
    }

    @Test
    void processPaymentThrowsExceptionForNegativeAmount() {
        final VenmoPayment payment = new VenmoPayment("@jake");
        assertThrows(IllegalArgumentException.class, () ->
                payment.processPayment(-20.0));
    }

    @Test
    void processPaymentThrowsExceptionForImproperDecimals() {
        final VenmoPayment payment = new VenmoPayment("@jake");
        assertThrows(IllegalArgumentException.class, () ->
                payment.processPayment(50.123));
    }

    @Test
    void processPaymentThrowsExceptionForNoAtSign() {
        final VenmoPayment payment = new VenmoPayment("45");
        assertThrows(IllegalArgumentException.class, () ->
                payment.processPayment(35.0));
    }

    @Test
    void processPaymentThrowsExceptionForAtSignNotFirst() {
        final VenmoPayment payment = new VenmoPayment("jake@");
        assertThrows(IllegalArgumentException.class, () ->
                payment.processPayment(35.0));
    }
}
