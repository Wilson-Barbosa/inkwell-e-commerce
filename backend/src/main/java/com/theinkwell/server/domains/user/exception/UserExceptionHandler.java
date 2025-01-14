package com.theinkwell.server.domains.user.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.theinkwell.server.domains.utils.StandardException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class UserExceptionHandler {
    
    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<StandardException> handleUserAlreadyRegisteredException(HttpServletRequest request,
                                                                                  Exception e){
        
        StandardException responseBody = new StandardException();
        responseBody.setMessage(e.getMessage());
        responseBody.setTime(Instant.now());
        responseBody.setPath(request.getRequestURI());        
                                                                                    
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBody);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<StandardException> handleAuthenticationException(HttpServletRequest request, Exception e) {
        
        StandardException responseBody = new StandardException();
        responseBody.setMessage(e.getMessage());
        responseBody.setTime(Instant.now());
        responseBody.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);                                                                 
    }


}
