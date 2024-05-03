package com.akash.policy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.akash.model.DoublyLinkedList;
import com.akash.model.DoublyLinkedListNode;

public class LRUEvictionPolicy<K> implements IEvictionPolicy<K> {

    Map<K, DoublyLinkedListNode<K>> map;
    DoublyLinkedList<K> list;

    public LRUEvictionPolicy() {
        list = new DoublyLinkedList<K>();
        map = new HashMap<>();
    }

    @Override
    public K evict() {
        return list.removeLast().val;
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


    
}