package ru.job4j.ood.lsp.productstorage;

import java.util.List;

public interface Store {
    void add(Food food);

    List<Food> getAll();

    boolean accept(Food food);

    void clear();
}
