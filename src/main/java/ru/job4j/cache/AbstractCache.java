package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public final V get(K key) {
        SoftReference<V> ref = cache.get(key);
        V value = (ref != null) ? ref.get() : null;

        if (value == null) {
            value = load(key);
            if (value != null) {
                put(key, value);
            }
        }
        return value;
    }

    protected abstract V load(K key);
}
