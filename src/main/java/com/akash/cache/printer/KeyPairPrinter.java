package com.akash.cache.printer;

public class KeyPairPrinter<K,V> {

    public void print(K key, V val) {
        System.out.println("(" + key + ", " + val + ")");
    }
}
