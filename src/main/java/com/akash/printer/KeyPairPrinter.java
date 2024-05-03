package com.akash.printer;

public class KeyPairPrinter<K,V> {

    public void print(K key, V val) {
        System.out.println("(" + key + ", " + val + ")");
    }
}
