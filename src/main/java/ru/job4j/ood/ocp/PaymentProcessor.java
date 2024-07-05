package ru.job4j.ood.ocp;

public class PaymentProcessor {
    public void processPayment(String paymentType, double amount) {
        if (paymentType.equals("CreditCard")) {
            processCreditCardPayment(amount);
        } else if (paymentType.equals("PayPal")) {
            processPayPalPayment(amount);
        }
    }

    private void processCreditCardPayment(double amount) {
    }

    private void processPayPalPayment(double amount) {
    }
}
