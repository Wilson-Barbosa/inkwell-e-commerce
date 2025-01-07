package com.theinkwell.server.domains.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.theinkwell.server.domains.user.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
    
    @Query(value = "SELECT p FROM Person p WHERE p.email = ?1")
    Person getUserCredentialsByEmail(String email);
    
}
