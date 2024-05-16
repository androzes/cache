package com.akash.cache;

import java.util.ArrayList;
import java.util.List;

import com.akash.cache.policy.FIFOEvictionPolicy;
import com.akash.cache.policy.IEvictionPolicy;
import com.akash.cache.policy.LFUEvictionPolicy;
import com.akash.cache.policy.LRUEvictionPolicy;
import com.akash.cache.storage.HashMapStorage;
import com.akash.cache.storage.IStorage;
import com.akash.cache.storage.exception.KeyNotFoundException;

public class Cache<K,V> implements ICache<K,V> {
    
    private IStorage<K,V> storage;
    private IEvictionPolicy<K> evictionPolicy;
    private int capacity;

    private Cache(IStorage<K,V> storage, IEvictionPolicy<K> evictionPolicy, int capacity) {
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
        this.capacity = capacity;
    }

    public V get(K key) {
        try {
            V val = storage.get(key);
            evictionPolicy.keyAccessed(key);
            return val;
        } catch (KeyNotFoundException e) {
            // log here
            //System.out.println("Key: " + key + " does not exist in the cache");
        }

        return null;
    }

    public void put(K key, V val) {
        try {
            V oldVal = storage.get(key);
        } catch (KeyNotFoundException e) {
             if (storage.size() >= capacity) {
                K removedKey = evictionPolicy.evict();
                System.out.println("Evicting " + removedKey + " from cache: " + evictionPolicy);
                storage.remove(removedKey);
            }
        }
        
        storage.add(key, val);
        evictionPolicy.keyAccessed(key);
    }

    public List<V> getAll() {
        List<K> keys = evictionPolicy.getAllKeys();
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

    public int size() {
        return storage.size();
    }

    public String toString() {
        return evictionPolicy.toString();
    }

    public static class CacheBuilder<K,V> {
        private IStorage<K,V> storage;
        private IEvictionPolicy<K> evictionPolicy;
        private int capacity;

        private final int DEFAULT_CAPACITY = 10;

        public CacheBuilder() {
            this.storage = new HashMapStorage<>();
            this.capacity = DEFAULT_CAPACITY;
            this.withDefaultEvictionPolicy();
        }

        private CacheBuilder<K,V> withDefaultEvictionPolicy() {
            return this.withFIFOEvictionPolicy();
        }

        public CacheBuilder<K,V> withFIFOEvictionPolicy() {
            this.evictionPolicy = new FIFOEvictionPolicy<>();
            return this;
        }

        public CacheBuilder<K,V> withLRUEvictionPolicy() {
            this.evictionPolicy = new LRUEvictionPolicy<>();
            return this;
        }

        public CacheBuilder<K,V> withLFUEvictionPolicy() {
            this.evictionPolicy = new LFUEvictionPolicy<>();
            return this;
        }

        public CacheBuilder<K, V> withCapacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public Cache<K,V> build() {
            return new Cache<K,V>(storage, evictionPolicy, capacity);
        }
        
    }

}
