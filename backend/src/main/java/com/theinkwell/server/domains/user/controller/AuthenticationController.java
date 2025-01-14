package com.theinkwell.server.domains.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theinkwell.server.domains.user.dto.LoginRequest;
import com.theinkwell.server.domains.user.dto.LoginResponse;
import com.theinkwell.server.domains.user.dto.RegisterRequest;
import com.theinkwell.server.domains.user.service.AuthenticationService;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {
    
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    /**
     * Returns a ResponseEntity with an empty body in case of a successful register request.
     */
    @PostMapping("register")
    public ResponseEntity<Void> registerNewUser(@RequestBody RegisterRequest requestDto){
        authenticationService.registerNewUser(requestDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Returns a ResponseEntity with a jwt token inside the response's body.
     */
    @PostMapping("login")
    public ResponseEntity<LoginResponse> logUser(@RequestBody LoginRequest requestDto){
        return ResponseEntity.status(HttpStatus.OK)
                             .body(authenticationService.logUserAndReturnToken(requestDto));
    }


}
