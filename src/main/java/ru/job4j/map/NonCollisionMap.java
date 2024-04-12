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
        boolean result = table[indexFor(hash(key.hashCode()))] == null;
        if (!result) {
            table[indexFor(hash(key.hashCode()))] = new MapEntry<>(key, value);
            return true;
        }
        return false;
    }

    private int hash(int hashCode) {
        return hashCode ^ hashCode >>> 16;
    }

    private int indexFor(int hash) {
        return hash(hash & (capacity - 1));
    }

    private void expand() {

    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public boolean remove(K key) {
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                while (count < table.length && table[count] == null) {
                    count++;
                }
                return count < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (K) table[count++];
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

    public static void main(String[] args) {
        NonCollisionMap<Integer, String> nonCollisionMaps = new NonCollisionMap<>();
        System.out.println(nonCollisionMaps.hash(0));
        System.out.println(nonCollisionMaps.hash(65535));
        System.out.println(nonCollisionMaps.hash(65536));
        System.out.println(nonCollisionMaps.indexFor(0));
        System.out.println(nonCollisionMaps.indexFor(7));
        System.out.println(nonCollisionMaps.indexFor(8));
        Map<Integer, String> map = new HashMap<>();
    }
}
