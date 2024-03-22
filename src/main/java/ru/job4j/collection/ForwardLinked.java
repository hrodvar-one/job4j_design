package ru.job4j.collection;

import java.util.*;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        Node<T> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        modCount++;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            if (current.next == null) {
                throw new IndexOutOfBoundsException("Index out of bounds");
            }
            current = current.next;
        }
        return current.item;
    }

    public T deleteFirst() {
        Node<T> f = head;
        if (f == null) {
            throw new NoSuchElementException();
        }
        T element = f.item;
        Node<T> next = f.next;
        f.item = null;
        f.next = null;
        head = next;
        size--;
        modCount++;
        return element;
    }

    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;

        return new Iterator<>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = current.item;
                current = current.next;
                return data;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}
