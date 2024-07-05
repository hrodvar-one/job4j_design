package ru.job4j.ood.ocp;

public class DiscountCalculator {
    public double calculateDiscount(String discountType, double amount) {
        if (discountType.equals("Seasonal")) {
            return amount * 0.1;
        } else if (discountType.equals("Clearance")) {
            return amount * 0.5;
        }
        return 0;
    }
}
