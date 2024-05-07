package ru.job4j.serialization.json;

import java.util.Arrays;

public class Car {
    private final boolean isDiesel;
    private final int yearOfManufacture;
    private final FuelTank fuelTank;
    private final String[] statuses;

    public Car(boolean isDiesel, int yearOfManufacture, FuelTank fuelTank, String[] statuses) {
        this.isDiesel = isDiesel;
        this.yearOfManufacture = yearOfManufacture;
        this.fuelTank = fuelTank;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Car{"
                + "isDiesel=" + isDiesel
                + ", yearOfManufacture=" + yearOfManufacture
                + ", fuelTank=" + fuelTank
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }
}
