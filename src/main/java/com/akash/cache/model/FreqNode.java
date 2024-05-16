package com.akash.cache.model;

public class FreqNode<K> implements Comparable<FreqNode<K>> {
    public K key;
    public int freq;

    public FreqNode(K key) {
        this.key = key;
        this.freq = 0;
    }

    public int compareTo(FreqNode<K> otherNode) {
        if (this.freq < otherNode.freq) {
            return -1;
        } else if (this.freq > otherNode.freq) {
            return 1;
        } 

        return 0;
    }

    public String toString() {
        return "(" + key.toString() + ", " + freq + ")";
    }

}