package com.akash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import com.akash.cache.Cache;
import com.akash.policy.IEvictionPolicy;
import com.akash.policy.LRUEvictionPolicy;
import com.akash.printer.KeyPairPrinter;
import com.akash.storage.HashMapStorage;
import com.akash.storage.IStorage;

// Problem Statement
 
// We have to do low level design for a Cache system. Cache that we will design will have to support following operations:
 
// Put: This will allow user to put a value against a key in the cache.
// Get: This will allow user to get the previously saved value using key.
// Eviction: Cache should also support removal of some key in case cache is full based on a predefined eviction policy, and we try to add new key value.
 
 
// Expectations
// Code should be functionally correct.
// Code should be modular and readable.
// Code should be extensible and scalable, Meaning it should be able to accommodate new requirements with minimal changes.
// Code should have good OOPs design.

// 


public class Main {
    public static void main(String[] args) {
        int capacity = 20;
        int numCachableObjects = 50;
        int numCacheGets = 20;

        int UPPER_BOUND = 100;
        int LOWER_BOUND = 0;

        IStorage<Integer, Integer> storage = new HashMapStorage<>();
        IEvictionPolicy<Integer> evictionPolicy = new LRUEvictionPolicy<>();
        Cache<Integer, Integer> lruCache = new Cache<>(storage, evictionPolicy, capacity);
        KeyPairPrinter<Integer, Integer> printer = new KeyPairPrinter<>();


        List<Integer> numsToAdd = randomNumbers(numCachableObjects, UPPER_BOUND, LOWER_BOUND);
        List<Integer> numsToGet = randomNumbers(numCacheGets, UPPER_BOUND, LOWER_BOUND);

        
        //Collections.sort(numsToAdd);
        System.out.println("Adding to cache: " + numsToAdd);
        HashSet<Integer> set = new HashSet(numsToAdd);
        System.out.println(set.size() + " unique values added to cache: " +  set);
        System.out.println("Getting from cache: " + numsToGet);


        for (int num: numsToAdd) {
            lruCache.put(num, num);
        }

        List<Integer> valuesInCache = lruCache.getAll();
        Collections.sort(valuesInCache);
        System.out.println(valuesInCache.size() + " values in cache: " +  valuesInCache);

        for (int num: numsToGet) {
            printer.print(num, lruCache.get(num));
        }
        
    }

    public static List<Integer> randomNumbers(int size, int upperBound, int lowerBound) {
        List<Integer> picked = new ArrayList<>();

        Random rnd = new Random();

        while (picked.size() < size) {
            picked.add(rnd.nextInt(lowerBound, upperBound));
        }
        return picked;
    }

}