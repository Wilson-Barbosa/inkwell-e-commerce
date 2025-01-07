package com.theinkwell.server.domains.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class UserExceptionHandler {
    
    @ExceptionHandler
    public ResponseEntity<String> handleUserAlreadyRegisteredException(HttpServletRequest request, UserAlreadyRegisteredException e){
        // TODO change the body to an appropriate response (probably)
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

}
