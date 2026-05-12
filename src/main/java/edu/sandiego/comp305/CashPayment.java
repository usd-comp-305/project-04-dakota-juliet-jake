package edu.sandiego.comp305;

public class CashPayment implements PaymentMethod{

    private static final double CENT_PRECISION = 100.0;

    @Override
    public boolean processPayment(final double amount) {
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
