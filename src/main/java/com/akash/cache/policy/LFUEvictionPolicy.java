package com.akash.cache.policy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import com.akash.cache.model.FreqNode;

public class LFUEvictionPolicy<K> implements IEvictionPolicy<K> {

    Map<K, FreqNode<K>> map;
    PriorityQueue<FreqNode<K>> queue;

    public LFUEvictionPolicy() {
        queue = new PriorityQueue<>();
        map = new HashMap<>();
    }

    @Override
    public K evict() {
        FreqNode<K> node = queue.poll();
        map.remove(node.key);
        return node.key;
    }

    @Override
    public void keyAccessed(K key) {
        if (!map.containsKey(key)) {
            FreqNode<K> node = new FreqNode<K>(key);
            map.put(key, node);
            queue.offer(node);
        } else {
            // increment freq & update priority queue
            FreqNode<K> node = map.get(key);
            queue.remove(node);
            node.freq += 1;
            queue.offer(node);

        }
    }

    @Override
    public List<K> getAllKeys() {
        List<K> keys = new ArrayList<>();
        Queue<FreqNode<K>> q = new PriorityQueue<>(queue);
        while (!q.isEmpty()) {
            keys.add(q.poll().key);
        }

        return keys;
    }

    public String toString() {
        return queue.toString();
    }
    
} 