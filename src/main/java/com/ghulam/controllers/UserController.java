package com.ghulam.controllers;

import com.ghulam.dtos.UserDto;
import com.ghulam.response.Response;
import com.ghulam.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    private ResponseEntity<Object> response(String message, Object data) {
        return Response.builder(message, HttpStatus.OK, data);
    }
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add-user")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto result = userService.createUser(userDto);
        return response("Added a new user", result);
    }

    @GetMapping("/get-user/{userId}")
    public ResponseEntity<Object> readUser(@PathVariable Long userId) {
        UserDto result = userService.getUserById(userId);
        return response("Read a user by id: " + userId, result);
    }

    @PutMapping("/update-user/{userId}")
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Long userId) {
        UserDto result = userService.updateUserById(userDto, userId);
        return response("Updated a user by id: " + userId, result);
    }

    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long userId) {
        UserDto result = userService.deleteUserById(userId);
        return response("Deleted a user by id: " + userId, result);
    }

    @GetMapping("/all-users")
    public ResponseEntity<Object> getAllUser() {
        List<UserDto> result = userService.getAllUsers();
        return response("Getting all users", result);
    }
}
