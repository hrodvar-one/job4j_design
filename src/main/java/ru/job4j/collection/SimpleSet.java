package ru.job4j.collection;

import java.util.Arrays;
import java.util.Objects;

public class SimpleSet {
    private String[] container = new String[2];
    private int size;

    public boolean add(String value) {
        if (size == container.length) {
            grow();
        }
        boolean result = !contains(value);
        if (result) {
            container[size++] = value;
        }
        return result;
    }

    private boolean contains(String value) {
        boolean result = false;
        for (String element : container) {
            if (Objects.equals(value, element)) {
                result = true;
                break;
            }
        }
        return result;
    }

    private void grow() {
        container = Arrays.copyOf(container, container.length * 2);
    }
}
