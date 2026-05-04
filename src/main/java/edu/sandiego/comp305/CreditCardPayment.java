package edu.sandiego.comp305;

public class CreditCardPayment implements PaymentMethod{
    private final String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean processPayment(final double amount){
        return false;
    }
}
