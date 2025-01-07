package com.theinkwell.server.domains.user.service;

import java.time.Instant;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.theinkwell.server.domains.user.dto.request.RegisterRequest;
import com.theinkwell.server.domains.user.enums.RoleEnum;
import com.theinkwell.server.domains.user.exception.UserAlreadyRegisteredException;
import com.theinkwell.server.domains.user.model.Person;
import com.theinkwell.server.domains.user.model.Role;
import com.theinkwell.server.domains.user.repository.PersonRepository;

public class AuthenticationService implements UserDetailsService{

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(PersonRepository personRepository, PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return personRepository.getUserCredentialsByEmail(username);
    }

    /**
     * Persists a new user if the provided credentials are valid.
     * By default a user will receive the Customer Role
     * 
     * @param request the http request body
     * @throws UserAlreadyRegisteredException if this email specified si already in the database
     */
    public void registerNewUser(RegisterRequest request) {

        if (loadUserByUsername(request.getEmail()) != null) {

            Person newUser = new Person();
            newUser.setCreatedAt(Instant.now());
            newUser.setEmail(request.getEmail());   
            newUser.setPassword(passwordEncoder.encode(request.getPassword()));
            newUser.setRole(new Role(RoleEnum.CUSTOMER));

            personRepository.save(newUser);

        } else {
            throw new UserAlreadyRegisteredException("User already registered");
        }
   
    }
    
}
