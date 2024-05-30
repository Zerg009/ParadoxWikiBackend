package com.paradoxwikibackend.exception;

public class ParadoxNotFoundException extends RuntimeException {
    public ParadoxNotFoundException(String message) {
        super(message);
    }
}