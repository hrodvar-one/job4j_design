package ru.job4j.ood.lsp.carparking;

public class Truck implements Car {
    private final int size;

    public Truck(int size) {
        if (size <= 1) {
            throw new IllegalArgumentException("Truck size must be greater than 1");
        }
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
