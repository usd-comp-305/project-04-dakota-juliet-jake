package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardPaymentTest {

    @Test
    void formatCardRemovesSpaces() {
        final String expectedCardNum = "1111222233334444";
        CreditCardPayment payment = new CreditCardPayment("1111 2222 3333 4444");

        payment.formatCardNumber();

        assertEquals(expectedCardNum, payment.getCardNumber());
    }

    @Test
    void processPaymentReturnsTrueForValidAmount() {
        CreditCardPayment payment = new CreditCardPayment("1111 2222 3333 4444");
        assertTrue(payment.processPayment(35.0));
    }

    @Test
    void processPaymentThrowsExceptionForNegativeAmount() {
        CreditCardPayment payment = new CreditCardPayment("1111 2222 3333 4444");
        assertThrows(IllegalArgumentException.class, () ->
                payment.processPayment(-20.0));
    }

    @Test
    void processPaymentThrowsExceptionForImproperDecimals() {
        CreditCardPayment payment = new CreditCardPayment("1111 2222 3333 4444");
        assertThrows(IllegalArgumentException.class, () ->
                payment.processPayment(50.123));
    }

    @Test
    void processPaymentThrowsExceptionForShortCardLength() {
        CreditCardPayment payment = new CreditCardPayment("1111");
        assertThrows(IllegalArgumentException.class, () ->
                payment.processPayment(35.0));
    }

    @Test
    void processPaymentThrowsExceptionForLongCardLength() {
        CreditCardPayment payment = new CreditCardPayment("1111 2222 3333 4444 5555");
        assertThrows(IllegalArgumentException.class, () ->
                payment.processPayment(35.0));
    }

    @Test
    void processPaymentReturnsTrueNoSpacesInCardNumber() {
        CreditCardPayment payment = new CreditCardPayment("1111222233334444");
        assertTrue(payment.processPayment(35.0));
    }

}