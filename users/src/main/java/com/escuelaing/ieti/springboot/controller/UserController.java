package com.escuelaing.ieti.springboot.controller;

import com.escuelaing.ieti.springboot.dto.UserDto;
import com.escuelaing.ieti.springboot.entities.User;
import com.escuelaing.ieti.springboot.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private final UserService userService;

    ModelMapper modelMapper = new ModelMapper();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/v2/users")
    public ResponseEntity<List<UserDto>> getAll() {
        try {
            List<User> users = userService.getAll();
            ArrayList<UserDto> data = new ArrayList<UserDto>();
            if (!users.isEmpty()) {
                for (User u : users) {
                    data.add(modelMapper.map(u, UserDto.class));
                }
            }
            return new ResponseEntity<List<UserDto>> (data, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("\n------------------------------------------------------------------------------");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping( "/api/v2/users/{id}" )
    public ResponseEntity<UserDto> findById( @PathVariable String id ) {
        try {
            User userTemp = userService.findById(id);
            if (userTemp != null) {
                return new ResponseEntity<UserDto>(modelMapper.map(userTemp, UserDto.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println("\n------------------------------------------------------------------------------");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/v2/users")
    public ResponseEntity<UserDto> create( @RequestBody UserDto userDto ) {
        try {
            User userMapped = new User();
            userMapped.toEntity(userDto);
            User newUser = userService.create(userMapped);
            if (newUser != null) {
                return new ResponseEntity<>(modelMapper.map(newUser, UserDto.class), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch (IllegalArgumentException iae) {
            System.out.println("\n------------------------------------------------------------------------------");
            System.out.println("IllegalArgumentException: The argument userDTO is null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println("\n------------------------------------------------------------------------------");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping( "/api/v2/users/{id}" )
    public ResponseEntity<UserDto> update( @RequestBody UserDto userDto, @PathVariable String id) {
        try {
            User userAux = new User();
            userAux.toEntity(userDto);
            User userTemp = userService.update(userAux, id);
            //User userTemp = userService.update(modelMapper.map(userDto, User.class), id);
            if (userTemp != null) {
                return new ResponseEntity<>(modelMapper.map(userTemp, UserDto.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println("\n------------------------------------------------------------------------------");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping( "/api/v2/users/{id}" )
    @RolesAllowed("ADMIN")
    public ResponseEntity<Boolean> delete( @PathVariable String id ) {
        try {
            boolean deleted = userService.deleteById(id);
            if (deleted) {
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println("\n------------------------------------------------------------------------------");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/api/v2/users/findUsersWithNameOrLastname/{queryText}")
    public ResponseEntity<List<UserDto>> findUsersWithNameOrLastname (@PathVariable String queryText) {
        try {
            List<User> users = userService.findUsersWithNameOrLastNameLike(queryText);
            ArrayList<UserDto> data = new ArrayList<UserDto>();
            if (!users.isEmpty()) {
                for (User u : users) {
                    data.add(modelMapper.map(u, UserDto.class));
                }
            }
            return new ResponseEntity<List<UserDto>> (data, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("\n------------------------------------------------------------------------------");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/v2/users/findUsersCreatedAfter/{startDate}")
    public ResponseEntity<List<UserDto>> findUsersCreatedAfter (@PathVariable String startDate) {
        try {
            List<User> users = userService.findUsersCreatedAfter(startDate);
            ArrayList<UserDto> data = new ArrayList<UserDto>();
            if (!users.isEmpty()) {
                for (User u : users) {
                    data.add(modelMapper.map(u, UserDto.class));
                }
            }
            return new ResponseEntity<List<UserDto>> (data, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("\n------------------------------------------------------------------------------");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
