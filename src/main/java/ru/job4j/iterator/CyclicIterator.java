package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {
    private List<T> data;
    private int index;

    public CyclicIterator(List<T> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (index == data.size()) {
            index = 0;
        }
        return index < data.size();
    }

    @Override
    public T next() {
        Object[] arrayList = data.toArray();
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return (T) arrayList[index++];
    }
}
