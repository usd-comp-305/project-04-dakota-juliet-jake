package edu.sandiego.comp305;

public class CashPayment implements PaymentMethod{
    private final double amountDue;
    public CashPayment(double amountDue) {
        this.amountDue = amountDue;
    }

    @Override
    public boolean processPayment(final double amountPaying) {
        if (amountPaying >= amountDue) {
            return true;
        }
        return false;
    }
}
