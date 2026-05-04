package edu.sandiego.comp305;

public class VenmoPayment implements PaymentMethod{
    private final String venmoHandle;

    public VenmoPayment(final String venmoHandle) {
        this.venmoHandle = venmoHandle;
    }

    @Override
    public boolean processPayment(final double amount) {
        return false;
    }
}
