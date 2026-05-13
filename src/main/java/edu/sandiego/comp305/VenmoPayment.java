package edu.sandiego.comp305;

import java.math.BigDecimal;

public class VenmoPayment implements PaymentMethod{

    private static final int MAX_DECIMAL_PLACES = 2;

    private final String venmoHandle;

    public VenmoPayment(final String venmoHandle) {
        this.venmoHandle = venmoHandle;
    }

    public void validateVenmoHandle() {
        if (!(venmoHandle.charAt(0) == '@')) {
            throw new IllegalArgumentException(
                    "Venmo handle must begin with @.");
        }
    }

    @Override
    public boolean processPayment(final double amount) {
        validateVenmoHandle();

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
