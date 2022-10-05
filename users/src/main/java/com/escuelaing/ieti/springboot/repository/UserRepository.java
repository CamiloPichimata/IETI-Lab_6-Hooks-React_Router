package com.escuelaing.ieti.springboot.repository;

import com.escuelaing.ieti.springboot.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    List<User> findByNameOrLastName (String name, String lastName);

    List<User> findByCreatedAtAfter (String createdAt);

    User findByEmail(String email);
}
