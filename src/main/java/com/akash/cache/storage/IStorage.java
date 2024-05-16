package com.akash.cache.storage;

import java.util.List;

import com.akash.cache.storage.exception.KeyNotFoundException;

public interface IStorage<K,V> {

    void add(K key, V val);

    V get(K key) throws KeyNotFoundException;

    void remove(K key);

    int size();
    
    List<V> getAll();
}
