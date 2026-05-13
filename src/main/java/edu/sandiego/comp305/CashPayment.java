package edu.sandiego.comp305;

import java.math.BigDecimal;

public class CashPayment implements PaymentMethod{

    private static final int MAX_DECIMAL_PLACES = 2;

    @Override
    public boolean processPayment(final double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Payment amount must be positive");
        }
        if (BigDecimal.valueOf(amount).scale() > MAX_DECIMAL_PLACES) {
            throw new IllegalArgumentException(
                    "Payment amount cannot have more than 2 decimal places");
        }
        return true;
    }
}
