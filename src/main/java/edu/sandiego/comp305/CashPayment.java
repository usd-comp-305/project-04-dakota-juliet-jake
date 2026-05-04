package edu.sandiego.comp305;

public class CashPayment implements PaymentMethod{
    @Override
    public boolean processPayment(final double amount) {
        return false;
    }
}
