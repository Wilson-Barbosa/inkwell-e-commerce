package com.theinkwell.server.domains.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theinkwell.server.domains.user.dto.PersonalProfileResponse;
import com.theinkwell.server.domains.user.service.CustomerService;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }
    
    @GetMapping("{userId}/personal-information")
    public ResponseEntity<PersonalProfileResponse> getPersonalInfo(@RequestParam long userId){
        return ResponseEntity.ok(customerService.getPersonalProfileResponse(userId));
    }
    
    
}
