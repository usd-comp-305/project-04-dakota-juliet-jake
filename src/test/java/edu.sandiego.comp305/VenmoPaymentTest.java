package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VenmoPaymentTest {

    @Test
    void processPaymentReturnsTrueForValidAmount() {
        VenmoPayment payment = new VenmoPayment("@jake");
        assertTrue(payment.processPayment(35.0));
    }

    @Test
    void processPaymentThrowsExceptionForNegativeAmount() {
        VenmoPayment payment = new VenmoPayment("@jake");
        assertThrows(IllegalArgumentException.class, () ->
                payment.processPayment(-20.0));
    }

    @Test
    void processPaymentThrowsExceptionForImproperDecimals() {
        VenmoPayment payment = new VenmoPayment("@jake");
        assertThrows(IllegalArgumentException.class, () ->
                payment.processPayment(50.123));
    }

    @Test
    void processPaymentThrowsExceptionForNoAtSign() {
        VenmoPayment payment = new VenmoPayment("45");
        assertThrows(IllegalArgumentException.class, () ->
                payment.processPayment(35.0));
    }

    @Test
    void processPaymentThrowsExceptionForAtSignNotFirst() {
        VenmoPayment payment = new VenmoPayment("jake@");
        assertThrows(IllegalArgumentException.class, () ->
                payment.processPayment(35.0));
    }
}
