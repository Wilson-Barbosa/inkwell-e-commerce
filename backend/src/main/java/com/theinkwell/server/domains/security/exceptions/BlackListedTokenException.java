package com.theinkwell.server.domains.security.exceptions;

/**
 * Exception thrown when a token is submitted as authorization, but thw jwt is
 * located inside the black list.
 */
public class BlackListedTokenException extends CustomAuhenticationException{
    public BlackListedTokenException(String message) {
        super(message);
    }
}
