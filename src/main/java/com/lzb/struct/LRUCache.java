package com.lzb.struct;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {
    private int capacity;
    private Map<Integer, Integer> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            private static final long serialVersionUID = -5558376059926770673L;

            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        return -1;
    }

    public void set(int key, int value) {
        cache.put(key, value);
    }
}
