package ru.job4j.map;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        if (count >= LOAD_FACTOR * capacity) {
            expand();
        }
        int index = indexForKey(key);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            modCount++;
            count++;
            result = true;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ hashCode >>> 16;
    }

    private int indexFor(int hash) {
        return hash(hash & (capacity - 1));
    }

    private void expand() {
        MapEntry<K, V>[] tmp = table;
        capacity *= 2;
        table = new MapEntry[capacity];
        count = 0;
        for (MapEntry<K, V> entry : tmp) {
            if (entry != null) {
                int index = indexForKey(entry.key);
                table[index] = entry;
            }
        }
    }

    private int indexForKey(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private boolean keyComparison(K key) {
        int index = indexForKey(key);
        return Objects.hashCode(table[index].key) == Objects.hashCode(key) && Objects.equals(table[index].key, key);
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = indexForKey(key);
        if (table[index] != null) {
            if (keyComparison(key)) {
                result = table[index].value;
            }
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = indexForKey(key);
        if (table[index] != null) {
            if (keyComparison(key)) {
                table[index].value = null;
                table[index] = null;
                modCount++;
                count--;
                result = true;
            }
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            int expectedModCount = modCount;
            int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}