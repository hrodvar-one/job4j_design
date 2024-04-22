package ru.job4j.tree;

import java.util.Optional;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> parentNode = findBy(parent);
        Node<E> childNode = new Node<>(child);
        if (parentNode.isPresent() && findBy(child).isEmpty()) {
            parentNode.get().children.add(childNode);
            result = true;
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(element -> element.value.equals(value));
    }

    public boolean isBinary() {
        Optional<Node<E>> result = findByPredicate(element -> element.children.size() > 2);
        return result.isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (condition.test(element)) {
                result = Optional.of(element);
                break;
            }
            data.addAll(element.children);
        }
        return result;
    }
}