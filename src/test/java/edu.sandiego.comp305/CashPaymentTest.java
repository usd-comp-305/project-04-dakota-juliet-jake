package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CashPaymentTest {

    @Test
    void processPaymentReturnsTrueForValidAmount() {
        final CashPayment payment = new CashPayment();
        assertTrue(payment.processPayment(35.0));
    }

    @Test
    void processPaymentThrowsExceptionForNegativeAmount() {
        final CashPayment payment = new CashPayment();
        assertThrows(IllegalArgumentException.class, () ->
                payment.processPayment(-20.0));
    }

    @Test
    void processPaymentThrowsExceptionForImproperDecimals() {
        final CashPayment payment = new CashPayment();
        assertThrows(IllegalArgumentException.class, () ->
                payment.processPayment(50.123));
    }
}
