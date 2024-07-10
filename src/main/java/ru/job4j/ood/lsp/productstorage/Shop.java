package ru.job4j.ood.lsp.productstorage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Shop extends AbstractStore {

    @Override
    public boolean accept(Food food) {
        long shelfLife = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        long daysSpent = ChronoUnit.DAYS.between(food.getCreateDate(), LocalDate.now());
        double spentPercentage = (double) daysSpent / shelfLife;
        return spentPercentage >= 0.25 && spentPercentage <= 0.75 || spentPercentage > 0.75 && spentPercentage < 1;
    }

    @Override
    public void add(Food food) {
        long shelfLife = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        long daysSpent = ChronoUnit.DAYS.between(food.getCreateDate(), LocalDate.now());
        double spentPercentage = (double) daysSpent / shelfLife;
        if (spentPercentage > 0.75 && spentPercentage < 1) {
            food.setDiscount(20); // Применяем скидку 20%
        }
        super.add(food);
    }
}
