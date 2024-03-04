package ru.job4j.generics;

public class Node<T extends Comparable<T>> {
    private Comparable data;
    private Node next;

    public Node(Comparable data, Node next) {
        this.data = data;
        this.next = next;
    }

    public Comparable getData() {
        return data;
    }
}
