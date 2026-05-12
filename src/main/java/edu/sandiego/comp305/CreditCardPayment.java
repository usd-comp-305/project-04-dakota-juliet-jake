package edu.sandiego.comp305;

public class CreditCardPayment implements PaymentMethod{
    private String cardNumber;

    private static final double CENT_PRECISION = 100.0;

    public CreditCardPayment(final String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    void formatCardNumber() {
        cardNumber = cardNumber.replaceAll("\\s", "");
    }

    private void checkCardLength() {
        if (cardNumber.length() != 16) {
            throw new IllegalArgumentException(
                    "Card number must be 16 digits");
        }
    }

    @Override
    public boolean processPayment(final double amount){
        formatCardNumber();

        checkCardLength();

        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Payment amount must be positive");
        }
        if (Math.round(amount * CENT_PRECISION) != amount * CENT_PRECISION) {
            throw new IllegalArgumentException(
                    "Payment amount cannot have more than 2 decimal places");
        }
        return true;
    }
}
