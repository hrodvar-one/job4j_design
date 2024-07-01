package ru.job4j.clone;

public class InnerObject {
    int num;

    @Override
    protected InnerObject clone() throws CloneNotSupportedException {
        return (InnerObject) super.clone();
    }
}
