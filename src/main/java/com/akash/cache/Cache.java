package com.akash.cache;

import java.util.ArrayList;
import java.util.List;

import com.akash.policy.IEvictionPolicy;
import com.akash.storage.IStorage;
import com.akash.storage.exception.KeyNotFoundException;

public class Cache<K,V> implements ICache<K,V> {
    
    IStorage<K,V> storage;
    IEvictionPolicy<K> strategy;
    int capacity;

    public Cache(IStorage<K,V> storage, IEvictionPolicy<K> strategy, int capacity) {
        this.storage = storage;
        this.strategy = strategy;
        this.capacity = capacity;
    }

    public V get(K key) {
        try {
            V val = storage.get(key);
            strategy.keyAccessed(key);
            return val;
        } catch (KeyNotFoundException e) {
            // log here
            //System.out.println("Key: " + key + " does not exist in the cache");
        }

        return null;
    }

    public void put(K key, V val) {
        if (storage.size() >= capacity) {
            K removedKey = strategy.evict();
            storage.remove(removedKey);
        }
        storage.add(key, val);
        strategy.keyAccessed(key);
    }

    public List<V> getAll() {
        List<K> keys = strategy.getAllKeys();
        List<V> values = new ArrayList<>(keys.size());
        for(K key: keys) {
            try {
                values.add(storage.get(key));
            } catch (KeyNotFoundException e) {
                values.add(null);
            }
            
        }
        return values;
    }

}
