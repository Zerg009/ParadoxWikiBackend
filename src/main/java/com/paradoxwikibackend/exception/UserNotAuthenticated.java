package com.paradoxwikibackend.exception;

public class UserNotAuthenticated extends RuntimeException{
    public UserNotAuthenticated(String message) {
        super(message);
    }
}
