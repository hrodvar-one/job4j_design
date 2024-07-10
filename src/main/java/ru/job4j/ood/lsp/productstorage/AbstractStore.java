package ru.job4j.ood.lsp.productstorage;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    protected List<Food> storage = new ArrayList<>();

    @Override
    public void add(Food food) {
        storage.add(food);
    }

    @Override
    public List<Food> getAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public abstract boolean accept(Food food);
}
