package com.theinkwell.server.domains.user.service;

import org.springframework.stereotype.Service;

import com.theinkwell.server.domains.user.dto.PersonalProfileResponse;
import com.theinkwell.server.domains.user.repository.PersonRepository;

@Service
public class CustomerService {
    
    private final PersonRepository personRepository;

    public CustomerService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public PersonalProfileResponse getPersonalProfileResponse(long userId){
        return null;
    }

}
