package edu.sandiego.comp305;

import java.math.BigDecimal;

public class CreditCardPayment implements PaymentMethod{

    private static final int MAX_DECIMAL_PLACES = 2;

    private String cardNumber;

    public CreditCardPayment(final String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    void removeCardNumberSpaces() {
        cardNumber = cardNumber.replaceAll("\\s", "");
    }

    public void checkCardLength() {
        if (cardNumber.length() != 16) {
            throw new IllegalArgumentException(
                    "Card number must be 16 digits");
        }
    }

    private void checkCardAllNumbers() {
        removeCardNumberSpaces();
        for (int i = 0; i < cardNumber.length(); i++) {
            final char cardDigit = cardNumber.charAt(i);
            if (!Character.isDigit(cardDigit)) {
                throw new IllegalArgumentException(
                        "Card number must only be numbers");
            }
        }
    }

    @Override
    public boolean processPayment(final double amount){
        removeCardNumberSpaces();

        checkCardLength();

        checkCardAllNumbers();

        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Payment amount must be positive.");
        }
        if (BigDecimal.valueOf(amount).scale() > MAX_DECIMAL_PLACES) {
            throw new IllegalArgumentException(
                    "Payment amount cannot have more than 2 decimal places.");
        }
        return true;
    }
}
