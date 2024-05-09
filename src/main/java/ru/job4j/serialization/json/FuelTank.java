package ru.job4j.serialization.json;

public class FuelTank {
    private final int capacity;

    public FuelTank(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "FuelTank{"
                + "capacity='" + capacity + '\''
                + '}';
    }
}
