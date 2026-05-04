package edu.sandiego.comp305;

public interface PaymentMethod {
    Receipt processPayment(final double amount);
}
