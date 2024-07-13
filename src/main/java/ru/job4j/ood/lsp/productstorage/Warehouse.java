package ru.job4j.ood.lsp.productstorage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Warehouse extends AbstractStore {

    @Override
    public boolean accept(Food food) {
        long shelfLife = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        long daysSpent = ChronoUnit.DAYS.between(food.getCreateDate(), LocalDate.now());
        double spentPercentage = (double) daysSpent / shelfLife;
        return spentPercentage < 0.25;
    }

    @Override
    public void clear() {
        storage.clear();
    }
}
