package com.akash.cache.policy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class FIFOEvictionPolicy<K> implements IEvictionPolicy<K> {

    private Queue<K> list;
    private Set<K> keySet;

    public FIFOEvictionPolicy() {
        list = new LinkedList<>();
        keySet = new HashSet<>();
    }

    @Override
    public K evict() {
        K key = list.poll();
        keySet.remove(key);
        return key;
    }

    @Override
    public void keyAccessed(K key) {
        if (!keySet.contains(key)) {
            list.add(key);
            keySet.add(key);
        }
    }

    @Override
    public List<K> getAllKeys() {
        List<K> keys = new ArrayList<>();
        for(K key : list) {
            keys.add(key);
        }
        return keys;
    }

    public String toString() {
        return list.toString();
    }
    
}