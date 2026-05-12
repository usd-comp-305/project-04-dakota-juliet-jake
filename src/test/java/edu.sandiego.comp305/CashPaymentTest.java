package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CashPaymentTest {

    @Test
    void processPaymentReturnsTrueForEqualAmountPaying() {
        CashPayment payment = new CashPayment(35.0);
        assertTrue(payment.processPayment(35.0));
    }

    @Test
    void processPaymentReturnsTrueForGreaterAmountPaying() {
        CashPayment payment = new CashPayment(35.0);
        assertTrue(payment.processPayment(40.0));
    }

    @Test
    void processPaymentReturnsFalseForLessAmountPaying() {
        CashPayment payment = new CashPayment(35.0);
        assertFalse(payment.processPayment(30.0));
    }

    @Test
    void processPaymentThrowsExceptionForNegativeAmount() {
        CashPayment payment = new CashPayment(35.0);
        assertThrows(IllegalArgumentException.class, () ->
                payment.processPayment(-20.0));
    }
}