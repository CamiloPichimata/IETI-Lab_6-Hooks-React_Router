package com.escuelaing.ieti.springboot.service;

import com.escuelaing.ieti.springboot.dto.UserDto;
import com.escuelaing.ieti.springboot.entities.User;
import com.escuelaing.ieti.springboot.enums.RoleEnum;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceHashMap implements UserService{
    private final HashMap<String, User> users = new HashMap<>();

    public UserServiceHashMap () {
        UserDto userDto1 = new UserDto("1", "Camilo", "camilo@mail.com", "Pérez", "abc1234");
        User user1 = new User();
        user1.toEntity(userDto1);
        user1.setRoles(new ArrayList<RoleEnum>() {
            {
                add(RoleEnum.ADMIN);
                add(RoleEnum.USER);
            }
        });
        users.put(user1.getId(), user1);
    }

    @Override
    public User create(User user) {
        if (users.containsKey(user.getId())) {
            return null;
        } else {
            users.put(user.getId(), user);
            return users.get(user.getId());
        }
    }

    @Override
    public User findById(String id) {
        return users.get(id);
    }

    @Override
    public List<User> getAll() {
        Collection<User> values = users.values();
        return new ArrayList<User>(values);
    }

    @Override
    public boolean deleteById(String id) {
        User userTemp = users.remove(id);
        return userTemp != null;
    }

    @Override
    public User update(User user, String userId) {
        if (users.containsKey(userId)) {
            users.replace(userId, user);
        }
        return users.get(userId);
    }


    @Override
    public List<User> findUsersWithNameOrLastNameLike(String queryText) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> findUsersCreatedAfter(String startDate) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User findByEmail(String email) {
        ArrayList<User> usersList = new ArrayList<>(users.values());
        User userFind = null;
        for (User u : usersList) {
            if (Objects.equals(u.getEmail(), email)) {
                userFind = u;
            }
        }
        return userFind;
    }
}
