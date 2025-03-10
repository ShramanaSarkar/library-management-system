package com.lms.library_management_system.controller;

import com.lms.library_management_system.dto.UserDto;
import com.lms.library_management_system.entity.User;
import com.lms.library_management_system.service.UserService;
import com.lms.library_management_system.util.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/{addressId}")
    public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody UserDto user, @PathVariable int addressId) {
        return userService.saveUser(user, addressId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseStructure<User>> findUserById(@PathVariable int userId) {
        return userService.findUserById(userId);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ResponseStructure<User>> updateUser(@PathVariable int userId, @RequestBody UserDto user) {
        return userService.updateUser(userId, user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseStructure<User>> deleteUser(@PathVariable int userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<User>>> fetchAllUsers() {
        return userService.fetchAllUsers();
    }
}

