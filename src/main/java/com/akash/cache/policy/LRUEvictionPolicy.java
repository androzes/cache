package com.akash.cache.policy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.akash.cache.model.DoublyLinkedList;
import com.akash.cache.model.DoublyLinkedListNode;

public class LRUEvictionPolicy<K> implements IEvictionPolicy<K> {

    Map<K, DoublyLinkedListNode<K>> map;
    DoublyLinkedList<K> list;

    public LRUEvictionPolicy() {
        list = new DoublyLinkedList<K>();
        map = new HashMap<>();
    }

    @Override
    public K evict() {
        K evictedKey = list.removeLast().val;
        map.remove(evictedKey);
        return evictedKey;
    }

    @Override
    public void keyAccessed(K key) {
        if (!map.containsKey(key)) {
            DoublyLinkedListNode<K> node = new DoublyLinkedListNode<K>(key);
            map.put(key, node);
            list.add(node);
        } else {
            // move key to start of the list
            if (list.size() > 1) {
                DoublyLinkedListNode<K> node = map.get(key);
                list.remove(node);
                list.add(node);
            }
            
        }
    }

    @Override
    public List<K> getAllKeys() {
        return list.getAll();
    }

    public String toString() {
        return list.toString();
    }
    
}