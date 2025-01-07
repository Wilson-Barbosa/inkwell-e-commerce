package com.theinkwell.server.domains.user.exception;

/**
 * Exception thrown when a user's email is already persisted.
 */
public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException(String message){
        super(message);
    }
}
