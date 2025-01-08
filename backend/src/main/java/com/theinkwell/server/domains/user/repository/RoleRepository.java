package com.theinkwell.server.domains.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theinkwell.server.domains.user.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    
}
