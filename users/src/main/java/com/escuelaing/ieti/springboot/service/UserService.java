package com.escuelaing.ieti.springboot.service;

import com.escuelaing.ieti.springboot.entities.User;

import java.util.Date;
import java.util.List;

public interface UserService {

    User create(User user );

    User findById( String id );

    List<User> getAll();

    boolean deleteById( String id );

    User update(User user, String userId );


    List<User> findUsersWithNameOrLastNameLike(String queryText);

    List<User> findUsersCreatedAfter(String startDate);


    User findByEmail(String email);
}
