package com.akash.policy;

import java.util.List;

public interface IEvictionPolicy<K> {

    K evict();

    void keyAccessed(K key);

    List<K> getAllKeys();
    
}
