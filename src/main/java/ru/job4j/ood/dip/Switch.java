package ru.job4j.ood.dip;

public class Switch {

    private Light light;

    public Switch() {
        this.light = new Light();
    }

    public void operate() {
        light.turnOn();
    }
}
