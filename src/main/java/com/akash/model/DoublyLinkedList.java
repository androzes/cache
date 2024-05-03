package com.akash.model;

import java.util.ArrayList;
import java.util.List;

public class DoublyLinkedList<K> {
    DoublyLinkedListNode<K> head;
    DoublyLinkedListNode<K> tail;
    int size;

    public void add(DoublyLinkedListNode<K> node) {
        if (node == null) {
            throw new NullPointerException("Node cannot be null");
        }
        addFirst(node);
    }

    public void addFirst(DoublyLinkedListNode<K> node) {
        node.next = head;
        if (node.next == null) {
            tail = node;
        } else {
            node.next.prev = node;
        }
        head = node;
        size += 1;
    }

    public void addLast(DoublyLinkedListNode<K> node) {
        node.prev = tail;
        if (node.prev == null) {
            head = node;
        } else {
            node.prev.next = node;
        }
        tail = node;
        size += 1;
    }

    public DoublyLinkedListNode<K> remove(DoublyLinkedListNode<K> node) {
        if (size == 0)
            return node;
        
        if (node.prev == null && node.next == null) {
            head = tail = null;
        } else if (node.prev == null) {
            head = node.next;
            node.next.prev = null;
            node.next = null;
        } else if (node.next == null) {
            tail = node.prev;
            node.prev.next = null;
            node.prev = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = node.prev = null;
        }

        size -= 1;
        return node;
    }

    public DoublyLinkedListNode<K> removeLast() {
        return remove(tail);
    }

    public int size() {
        return size;
    }

    public List<K> getAll() {
        DoublyLinkedListNode<K> node = head;
        List<K> list = new ArrayList<>(size);
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        return list;
    }

    public DoublyLinkedListNode<K> head() {
        return head;
    }

    public DoublyLinkedListNode<K> tail() {
        return tail;
    }
}
 