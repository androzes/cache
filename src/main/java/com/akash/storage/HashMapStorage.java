package com.akash.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.akash.storage.exception.KeyNotFoundException;

public class HashMapStorage<K,V> implements IStorage<K,V>{
    HashMap<K,V> map;

    public HashMapStorage(){
        map = new HashMap<K, V>();
    }

    @Override
    public void add(K key, V val) {
       map.put(key, val);
    }

    @Override
    public V get(K key) throws KeyNotFoundException {
        V val = map.get(key);
        if (val == null) {
            throw new KeyNotFoundException();
        }
        return val;
    }

    @Override
    public void remove(K key) {
        map.remove(key);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public List<V> getAll() {
        return new ArrayList<V>(map.values());
    }

}