package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CashPaymentTest {

    @Test
    void processPaymentReturnsTrueForValidAmount() {
        CashPayment payment = new CashPayment();
        assertTrue(payment.processPayment(35.0));
    }

    @Test
    void processPaymentThrowsExceptionForNegativeAmount() {
        CashPayment payment = new CashPayment();
        assertThrows(IllegalArgumentException.class, () ->
                payment.processPayment(-20.0));
    }

    @Test
    void processPaymentThrowsExceptionForImproperDecimals() {
        CashPayment payment = new CashPayment();
        assertThrows(IllegalArgumentException.class, () ->
                payment.processPayment(50.123));
    }
}
