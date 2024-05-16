package com.akash.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.akash.cache.model.DoublyLinkedList;
import com.akash.cache.model.DoublyLinkedListNode;

public class DoublyLinkedListTest {
    
    
    @Test
    public void testAddToEmptyList() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        int[] values = new int[]{2};

        List<Integer> expectedValues = new ArrayList<>();


        for(int val: values) {
            list.add(new DoublyLinkedListNode<Integer>(val));
            expectedValues.add(val);
        }
        
        Assert.assertEquals(values.length, list.size());
        Assert.assertEquals(expectedValues, list.getAll());
        Assert.assertEquals(expectedValues.get(0), list.head().val);
        Assert.assertEquals(expectedValues.get(0), list.tail().val);
    }

    @Test
    public void testAddToNonEmptyList() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        int[] values = new int[]{2, 3, 4, 5};
        List<Integer> expectedValues = new ArrayList<>();

        for(int val: values) {
            list.add(new DoublyLinkedListNode<Integer>(val));
        }

        for (int i=values.length-1; i>=0; i--) {
            expectedValues.add(values[i]);
        }
        
        Assert.assertEquals(values.length, list.size());
        Assert.assertEquals(expectedValues, list.getAll());
        Assert.assertEquals(expectedValues.get(0), list.head().val);
        Assert.assertEquals(expectedValues.get(values.length - 1), list.tail().val);
    }

    @Test
    public void testAddNullToList() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        NullPointerException e = Assert.assertThrows(NullPointerException.class, () -> list.add(null));
        Assert.assertEquals(NullPointerException.class, e.getClass());
        Assert.assertEquals("Node cannot be null", e.getMessage());
    }

    @Test
    public void testAddFirstToEmptyList() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        int[] values = new int[]{2};

        List<Integer> expectedValues = new ArrayList<>();


        for(int val: values) {
            list.addFirst(new DoublyLinkedListNode<Integer>(val));
            expectedValues.add(val);
        }
        
        Assert.assertEquals(values.length, list.size());
        Assert.assertEquals(expectedValues, list.getAll());
        Assert.assertEquals(expectedValues.get(0), list.head().val);
        Assert.assertEquals(expectedValues.get(0), list.tail().val);
    }

    @Test
    public void testAddFirstToNonEmptyList() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        int[] values = new int[]{2, 3, 4, 5};
        List<Integer> expectedValues = new ArrayList<>();

        for(int val: values) {
            list.addFirst(new DoublyLinkedListNode<Integer>(val));
        }

        for (int i=values.length-1; i>=0; i--) {
            expectedValues.add(values[i]);
        }
        
        Assert.assertEquals(values.length, list.size());
        Assert.assertEquals(expectedValues, list.getAll());
        Assert.assertEquals(expectedValues.get(0), list.head().val);
        Assert.assertEquals(expectedValues.get(values.length - 1), list.tail().val);
    }

    @Test
    public void testAddFirstNullToList() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        NullPointerException e = Assert.assertThrows(NullPointerException.class, () -> list.addFirst(null));
        Assert.assertEquals(NullPointerException.class, e.getClass());
        //Assert.assertEquals("Node cannot be null", e.getMessage());
    }

    @Test
    public void testAddLastToEmptyList() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        int[] values = new int[]{2};

        List<Integer> expectedValues = new ArrayList<>();


        for(int val: values) {
            list.addLast(new DoublyLinkedListNode<Integer>(val));
            expectedValues.add(val);
        }
        
        Assert.assertEquals(values.length, list.size());
        Assert.assertEquals(expectedValues, list.getAll());
        Assert.assertEquals(expectedValues.get(0), list.head().val);
        Assert.assertEquals(expectedValues.get(0), list.tail().val);
    }

    @Test
    public void testAddLastToNonEmptyList() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        int[] values = new int[]{2, 3, 4, 5};
        List<Integer> expectedValues = new ArrayList<>();

        for(int val: values) {
            list.addLast(new DoublyLinkedListNode<Integer>(val));
            expectedValues.add(val);
        }

        Assert.assertEquals(values.length, list.size());
        Assert.assertEquals(expectedValues, list.getAll());
        Assert.assertEquals(expectedValues.get(0), list.head().val);
        Assert.assertEquals(expectedValues.get(values.length - 1), list.tail().val);
    }

    @Test
    public void testAddLastNullToList() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        NullPointerException e = Assert.assertThrows(NullPointerException.class, () -> list.addLast(null));
        Assert.assertEquals(NullPointerException.class, e.getClass());
        //Assert.assertEquals("Node cannot be null", e.getMessage());
    }

    @Test
    public void testRemoveInEmptyList() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        DoublyLinkedListNode<Integer> node = new DoublyLinkedListNode<Integer>(1);
        DoublyLinkedListNode<Integer> removedNode = list.remove(node);

        Assert.assertEquals(node, removedNode);
        Assert.assertEquals(0, list.size());
        Assert.assertEquals(new ArrayList<Integer>(), list.getAll());
        Assert.assertNull(list.head());
        Assert.assertNull(list.tail());
    }

    @Test
    public void testRemoveInListOfOne() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        DoublyLinkedListNode<Integer> node = new DoublyLinkedListNode<Integer>(1);

        list.addFirst(node);
        
        DoublyLinkedListNode<Integer> removedNode = list.remove(node);

        Assert.assertEquals(node, removedNode);
        Assert.assertEquals(0, list.size());
        Assert.assertEquals(new ArrayList<Integer>(), list.getAll());
        Assert.assertNull(list.head());
        Assert.assertNull(list.tail());
    }

    @Test
    public void testRemoveInBetweenInNonEmptyList() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        DoublyLinkedListNode<Integer> node1 = new DoublyLinkedListNode<Integer>(2);
        DoublyLinkedListNode<Integer> node2 = new DoublyLinkedListNode<Integer>(3);
        DoublyLinkedListNode<Integer> node3 = new DoublyLinkedListNode<Integer>(4);
        DoublyLinkedListNode<Integer> node4 = new DoublyLinkedListNode<Integer>(5);


        list.addLast(node1);
        list.addLast(node2);
        list.addLast(node3);
        list.addLast(node4);

        
        DoublyLinkedListNode<Integer> removedNode = list.remove(node3);

        int[] expectedValuesAfterRemove = new int[] {2, 3, 5};

        List<Integer> expectedValuesAfterRemoval = new ArrayList<>();
        for(int val: expectedValuesAfterRemove) {
            expectedValuesAfterRemoval.add(val);
        }

        Assert.assertEquals(node3, removedNode);
        Assert.assertEquals(3, list.size());
        Assert.assertEquals(expectedValuesAfterRemoval, list.getAll());
        Assert.assertEquals(node1, list.head());
        Assert.assertEquals(node4, list.tail());
    }

    @Test
    public void testRemoveAtHeadInNonEmptyList() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        DoublyLinkedListNode<Integer> node1 = new DoublyLinkedListNode<Integer>(2);
        DoublyLinkedListNode<Integer> node2 = new DoublyLinkedListNode<Integer>(3);
        DoublyLinkedListNode<Integer> node3 = new DoublyLinkedListNode<Integer>(4);
        DoublyLinkedListNode<Integer> node4 = new DoublyLinkedListNode<Integer>(5);


        list.addLast(node1);
        list.addLast(node2);
        list.addLast(node3);
        list.addLast(node4);

        DoublyLinkedListNode<Integer> removedNode = list.remove(node1);

        int[] expectedValuesAfterRemove = new int[] {3, 4, 5};

        List<Integer> expectedValuesAfterRemoval = new ArrayList<>();
        for(int val: expectedValuesAfterRemove) {
            expectedValuesAfterRemoval.add(val);
        }

        Assert.assertEquals(node1, removedNode);
        Assert.assertEquals(3, list.size());
        Assert.assertEquals(expectedValuesAfterRemoval, list.getAll());
        Assert.assertEquals(node2, list.head());
        Assert.assertEquals(node4, list.tail());
    }

    @Test
    public void testRemoveAtTailInNonEmptyList() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        DoublyLinkedListNode<Integer> node1 = new DoublyLinkedListNode<Integer>(2);
        DoublyLinkedListNode<Integer> node2 = new DoublyLinkedListNode<Integer>(3);
        DoublyLinkedListNode<Integer> node3 = new DoublyLinkedListNode<Integer>(4);
        DoublyLinkedListNode<Integer> node4 = new DoublyLinkedListNode<Integer>(5);


        list.addLast(node1);
        list.addLast(node2);
        list.addLast(node3);
        list.addLast(node4);

        DoublyLinkedListNode<Integer> removedNode = list.remove(node4);
        DoublyLinkedListNode<Integer> removedNode2 = list.remove(node2);

        int[] expectedValuesAfterRemove = new int[] {2, 4};

        List<Integer> expectedValuesAfterRemoval = new ArrayList<>();
        for(int val: expectedValuesAfterRemove) {
            expectedValuesAfterRemoval.add(val);
        }

        Assert.assertEquals(node4, removedNode);
        Assert.assertEquals(node2, removedNode2);
        Assert.assertEquals(2, list.size());
        Assert.assertEquals(expectedValuesAfterRemoval, list.getAll());
        Assert.assertEquals(node1, list.head());
        Assert.assertEquals(node3, list.tail());
    }

    @Test
    public void testRemoveLastInEmptyList() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        DoublyLinkedListNode<Integer> node = list.removeLast();
        
        Assert.assertNull(node);
        Assert.assertEquals(0, list.size());
        Assert.assertEquals(new ArrayList<>(), list.getAll());
        Assert.assertNull(list.head());
        Assert.assertNull(list.tail());
    }

    @Test
    public void testRemoveLastInListOfOne() {

        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        DoublyLinkedListNode<Integer> node = new DoublyLinkedListNode<Integer>(1);

        list.addFirst(node);
        
        DoublyLinkedListNode<Integer> removedNode = list.removeLast();
        
        Assert.assertEquals(node, removedNode);
        Assert.assertEquals(0, list.size());
        Assert.assertEquals(new ArrayList<>(), list.getAll());
        Assert.assertNull(list.head());
        Assert.assertNull(list.tail());
    }

    @Test
    public void testRemoveLastInNonEmptyList() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        int[] values = new int[]{2, 3, 4, 5};
        List<Integer> expectedValues = new ArrayList<>();

        for(int val: values) {
            list.add(new DoublyLinkedListNode<Integer>(val));
        }

        for (int i=values.length-1; i>0; i--) {
            expectedValues.add(values[i]);
        }

        DoublyLinkedListNode<Integer> node = list.removeLast();
        
        Assert.assertEquals(Integer.valueOf(values[0]), node.val);
        Assert.assertEquals(expectedValues.size(), list.size());
        Assert.assertEquals(expectedValues, list.getAll());
        Assert.assertEquals(expectedValues.get(0), list.head().val);
        Assert.assertEquals(expectedValues.get(expectedValues.size()-1), list.tail().val);
    }

}
