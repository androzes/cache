package com.akash;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import com.akash.cache.Cache;
import com.akash.cache.Cache.CacheBuilder;
import com.akash.cache.printer.KeyPairPrinter;

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
        int capacity = 10;
        int numCachableObjects = 50;
        int numCacheGets = 20;

        int UPPER_BOUND = 20;
        int LOWER_BOUND = 0;

        Cache<Integer, Integer> lruCache = new CacheBuilder<Integer, Integer>()
            .withCapacity(capacity)
            .withLRUEvictionPolicy()
            .build();

        Cache<Integer, Integer> lfuCache = new CacheBuilder<Integer, Integer>()
            .withCapacity(capacity)
            .withLFUEvictionPolicy()
            .build();

        Cache<Integer, Integer> fifoCache = new CacheBuilder<Integer, Integer>()
            .withCapacity(capacity)
            .withFIFOEvictionPolicy()
            .build();

        KeyPairPrinter<Integer, Integer> printer = new KeyPairPrinter<>();


        List<Integer> numsToAdd = randomNumbers(numCachableObjects, UPPER_BOUND, LOWER_BOUND);
        List<Integer> numsToGet = randomNumbers(numCacheGets, UPPER_BOUND, LOWER_BOUND);

        
        //Collections.sort(numsToAdd);
        
        HashSet<Integer> set = new HashSet<>(numsToAdd);
        
        System.out.println("-------------------------- LRU Cache Begin --------------------------");
        System.out.println("Adding to cache: " + numsToAdd);
        System.out.println(set.size() + " unique values added to cache: " +  set);
        
        for (int num: numsToAdd) {
            System.out.println("Adding " + num + " to LRU cache: " + lruCache);
            lruCache.put(num, num);
        }
        System.out.println(lruCache.size() + " values in LRU cache: " +  lruCache);
        System.out.println("Getting from cache: " + numsToGet);
        for (int num: numsToGet) {
            printer.print(num, lruCache.get(num));
        }
        System.out.println("-------------------------- LRU Cache End --------------------------");
        System.out.println();
        System.out.println();


        System.out.println("-------------------------- LFU Cache Start --------------------------");
        System.out.println("Adding to cache: " + numsToAdd);
        System.out.println(set.size() + " unique values added to cache: " +  set);
        for (int num: numsToAdd) {
            System.out.println("Adding " + num + " to LFU cache: " + lfuCache);
            lfuCache.put(num, num);
        }
        System.out.println(lfuCache.size() + " values in LFU cache: " +  lfuCache);
        
        System.out.println("Getting from cache: " + numsToGet);
        for (int num: numsToGet) {
            printer.print(num, lfuCache.get(num));
        }
        System.out.println("-------------------------- LFU Cache End --------------------------");
        System.out.println();
        System.out.println();


        System.out.println("-------------------------- FIFO Cache Begin --------------------------");
        System.out.println("Adding to cache: " + numsToAdd);
        System.out.println(set.size() + " unique values added to cache: " +  set);
        for (int num: numsToAdd) {            
            System.out.println("Adding " + num + " to FIFO cache: " + fifoCache);
            fifoCache.put(num, num);
        }
        System.out.println(fifoCache.size() + " values in FIFO cache: " +  fifoCache);
        
        System.out.println("Getting from cache: " + numsToGet);
        for (int num: numsToGet) {
            printer.print(num, fifoCache.get(num));
        }
        System.out.println("-------------------------- FIFO Cache End --------------------------");        
        
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