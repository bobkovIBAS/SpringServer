package com.example.SpringServer.repositories;


import com.example.SpringServer.model.ERole;
import com.example.SpringServer.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
