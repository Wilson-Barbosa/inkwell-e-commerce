package com.theinkwell.server.domains.user.service;

import java.time.Instant;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.theinkwell.server.domains.user.dto.request.RegisterRequest;
import com.theinkwell.server.domains.user.exception.UserAlreadyRegisteredException;
import com.theinkwell.server.domains.user.model.Customer;
import com.theinkwell.server.domains.user.model.Person;
import com.theinkwell.server.domains.user.repository.PersonRepository;
import com.theinkwell.server.domains.user.repository.RoleRepository;

@Service
public class AuthenticationService implements UserDetailsService{

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public AuthenticationService(PersonRepository personRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository){
        this.passwordEncoder = passwordEncoder;
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return personRepository.getUserCredentialsByEmail(username);
    }

    /**
     * <p>Persists a new user if the provided credentials are valid.</p>
     * <p>By default a user will receive the <code>Customer Role</code></p>
     * 
     * @param request DTO send as a part of a body's request
     * @throws UserAlreadyRegisteredException if this email specified is already in the database
     */
    public void registerNewUser(RegisterRequest request) {

        if (loadUserByUsername(request.getEmail()) == null) {   

            Person newUser = new Customer();      
            newUser.setEmail(request.getEmail());   
            newUser.setPassword(passwordEncoder.encode(request.getPassword()));
            newUser.setCreatedAt(Instant.now());
            newUser.setRole(roleRepository.findById(2).get()); // id 2 corresponds to the customer role

            personRepository.save(newUser);

        } else {
            throw new UserAlreadyRegisteredException("User already registered");
        }
        
    }
    
}
