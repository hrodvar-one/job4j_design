package ru.job4j.ood.lsp.productstorage;

import java.time.LocalDate;

public class Trash extends AbstractStore {

    @Override
    public boolean accept(Food food) {
        return LocalDate.now().isAfter(food.getExpiryDate()) || LocalDate.now().isEqual(food.getExpiryDate());
    }

    @Override
    public void clear() {
        storage.clear();
    }
}
