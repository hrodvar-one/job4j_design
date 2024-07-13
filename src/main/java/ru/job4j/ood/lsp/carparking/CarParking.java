package ru.job4j.ood.lsp.carparking;

import java.util.ArrayList;
import java.util.List;

public class CarParking implements Parking {
    private final int passengerCarSpaces;
    private final int truckSpaces;
    private final List<Car> passengerCars;
    private final List<Car> trucks;

    public CarParking(int passengerCarSpaces, int truckSpaces) {
        this.passengerCarSpaces = passengerCarSpaces;
        this.truckSpaces = truckSpaces;
        this.passengerCars = new ArrayList<>();
        this.trucks = new ArrayList<>();
    }

    @Override
    public boolean park(Car car) {
        if (car.getSize() == 1) {
            if (passengerCars.size() < passengerCarSpaces) {
                passengerCars.add(car);
                return true;
            }
        } else {
            if (trucks.size() < truckSpaces) {
                trucks.add(car);
                return true;
            } else if (passengerCars.size() + car.getSize() <= passengerCarSpaces) {
                for (int i = 0; i < car.getSize(); i++) {
                    passengerCars.add(car);
                }
                return true;
            }
        }
        return false;
    }

    public int getPassengerCarSpaces() {
        return passengerCarSpaces;
    }

    public int getTruckSpaces() {
        return truckSpaces;
    }
}
