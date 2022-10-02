package com.escuelaing.ieti.springboot.service;

import com.escuelaing.ieti.springboot.entities.User;
import com.escuelaing.ieti.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceMongoDB implements UserService {

    private final UserRepository userRepository;

    public UserServiceMongoDB(@Autowired UserRepository userRespository) {
        this.userRepository = userRespository;
    }

    @Override
    public User create(User user) {
        try {
            System.out.println("\n Creando usuario en La base de datos:\n" + user.getId() + ", " + user.getName() + ", " + user.getPasswordHash());
            userRepository.insert(user);
            Optional<User> userTemp = userRepository.findById(user.getId());
            System.out.println("User Registered: " + userTemp);
            return userTemp.orElse(null);
        } catch (DuplicateKeyException e) {
            System.out.println("The specified id is already registered");
            return null;
        }
    }

    @Override
    public User findById(String id) {
        try {
            System.out.println("idTemp = " + id);
            Optional<User> userTemp = userRepository.findById(id);
            return userTemp.orElse(null);
            //return userTemp.get();
        } catch (NoSuchElementException e) {
            System.out.println("The specified id is not registered");
            return null;
        }
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteById(String id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public User update(User user, String userId) {
        if (userRepository.existsById(userId)) {
            return userRepository.save(user);
        } else {
            System.out.println("The specified id is not registered");
            return null;
        }
    }


    @Override
    public List<User> findUsersWithNameOrLastNameLike(String queryText) {
        return userRepository.findByNameOrLastName(queryText, queryText);
    }

    @Override
    public List<User> findUsersCreatedAfter(String startDate) {
        return userRepository.findByCreatedAtAfter(startDate);
        //return null;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
