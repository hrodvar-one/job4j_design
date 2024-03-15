package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        modCount++;
        if (container.length == 0) {
            container = Arrays.copyOf(container, container.length + 1);
        }
        if (size >= container.length) {
            extendArray();
        }
        container[size++] = value;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T oldValue = container[index];
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        final Object[] es = container;
        @SuppressWarnings("unchecked") T oldValue = (T) es[index];
        modCount++;
        final int newSize;
        newSize = size - 1;
        if ((newSize) > index) {
            System.arraycopy(es, index + 1, es, index, newSize - index);
        }
        size = newSize;
        es[size] = null;
        return oldValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int cursor;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (size == 0) {
                    return false;
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < container.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[cursor++];
            }
        };
    }

    private void extendArray() {
        container = Arrays.copyOf(container, container.length * 2);
    }
}
