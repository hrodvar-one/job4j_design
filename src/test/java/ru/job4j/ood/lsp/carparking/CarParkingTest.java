package ru.job4j.ood.lsp.carparking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarParkingTest {

    private Parking parking;

    @BeforeEach
    void setUp() {
        parking = new CarParking(10, 2);
    }

    @Test
    void whenParkPassengerCarThenSuccess() {
        Car car = new PassengerCar();
        boolean result = parking.park(car);
        assertTrue(result);
    }

    @Test
    void whenParkTruckThenSuccess() {
        Car car = new Truck(2);
        boolean result = parking.park(car);
        assertTrue(result);
    }

    @Test
    void whenParkingFullThenFail() {
        for (int i = 0; i < 10; i++) {
            parking.park(new PassengerCar());
        }

        Car car = new PassengerCar();
        boolean result = parking.park(car);
        assertFalse(result);
    }

    @Test
    void whenTruckParksInPassengerSpacesThenSuccess() {
        parking = new CarParking(10, 1);

        Car truck = new Truck(2);
        boolean result = parking.park(truck);
        assertTrue(result);
    }
}