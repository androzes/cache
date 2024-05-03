package com.akash.model;

public class DoublyLinkedListNode<V> {

    public V val;

    public DoublyLinkedListNode<V> prev;
    public DoublyLinkedListNode<V> next;

    public DoublyLinkedListNode(V val) {
        this.val = val;
    }

}
