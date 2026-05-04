package edu.sandiego.comp305;

public class Receipt {
    private final double amount;
    private final String paymentType;

    public Receipt(final double amount, final String paymentType) {
        this.amount = amount;
        this.paymentType = paymentType;
    }

    public double getAmount() {
        return this.amount;
    }

    public String getPaymentType() {
        return this.paymentType;
    }

    public String toString() {
        return "";
    }
}
