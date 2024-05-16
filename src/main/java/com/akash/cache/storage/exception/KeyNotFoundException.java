package com.akash.cache.storage.exception;

public class KeyNotFoundException extends Exception{

    public KeyNotFoundException() {
        super("Key not found in storage");
    }
}
