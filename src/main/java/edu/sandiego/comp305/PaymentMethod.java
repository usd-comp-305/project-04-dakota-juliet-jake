package edu.sandiego.comp305;

public interface PaymentMethod {

    public abstract boolean processPayment(final double amount);
}
