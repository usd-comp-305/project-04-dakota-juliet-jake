package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardPaymentTest {

    @Test
    void formatCardRemovesSpaces() {
        final String expectedCardNum = "1111222233334444";
        final CreditCardPayment payment =
                new CreditCardPayment("1111 2222 3333 4444");

        payment.removeCardNumberSpaces();

        assertEquals(expectedCardNum, payment.getCardNumber());
    }

    @Test
    void processPaymentReturnsTrueForValidAmount() {
        final CreditCardPayment payment =
                new CreditCardPayment("1111 2222 3333 4444");
        assertTrue(payment.processPayment(35.0));
    }

    @Test
    void processPaymentThrowsExceptionForNegativeAmount() {
        final CreditCardPayment payment =
                new CreditCardPayment("1111 2222 3333 4444");
        assertThrows(IllegalArgumentException.class, () ->
                payment.processPayment(-20.0));
    }

    @Test
    void processPaymentThrowsExceptionForImproperDecimals() {
        final CreditCardPayment payment =
                new CreditCardPayment("1111 2222 3333 4444");
        assertThrows(IllegalArgumentException.class, () ->
                payment.processPayment(50.123));
    }

    @Test
    void processPaymentThrowsExceptionForShortCardLength() {
        final CreditCardPayment payment =
                new CreditCardPayment("1111");
        assertThrows(IllegalArgumentException.class, () ->
                payment.processPayment(35.0));
    }

    @Test
    void processPaymentThrowsExceptionForLongCardLength() {
        final CreditCardPayment payment =
                new CreditCardPayment("1111 2222 3333 4444 5555");
        assertThrows(IllegalArgumentException.class, () ->
                payment.processPayment(35.0));
    }

    @Test
    void processPaymentReturnsTrueNoSpacesInCardNumber() {
        final CreditCardPayment payment =
                new CreditCardPayment("1111222233334444");
        assertTrue(payment.processPayment(35.0));
    }

    @Test
    void processPaymentThrowsWithNonNumberInCardNumber() {
        final CreditCardPayment payment =
                new CreditCardPayment("1111 2b22 33!3 4444");
        assertThrows(IllegalArgumentException.class, () ->
                payment.processPayment(35.0));
    }

}
