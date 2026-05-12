package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CashPaymentTest {

    @Test
    void cashPaymentReturnsTrueForEqualAmountPaying() {
        CashPayment payment = new CashPayment(35.0);
        assertTrue(payment.processPayment(35.0));
    }

    @Test
    void cashPaymentReturnsTrueForGreaterAmountPaying() {
        CashPayment payment = new CashPayment(35.0);
        assertTrue(payment.processPayment(40.0));
    }

    @Test
    void cashPaymentReturnsFalseForLessAmountPaying() {
        CashPayment payment = new CashPayment(35.0);
        assertFalse(payment.processPayment(30.0));
    }

    @Test
    void cashPaymentThrowsExceptionForNegativeAmount() {

    }
}