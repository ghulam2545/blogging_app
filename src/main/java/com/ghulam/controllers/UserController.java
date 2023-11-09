package com.ghulam.controllers;

import com.ghulam.dtos.UserDto;
import com.ghulam.services.impl.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add-user")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto res = userService.createUser(userDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/get-user/{userId}")
    public ResponseEntity<Object> readUser(@PathVariable Long userId) {
        UserDto res = userService.getUserById(userId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/update-user/{userId}")
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Long userId) {
        UserDto res = userService.updateUserById(userDto, userId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/delete-user/{userId}")
    public  ResponseEntity<Object> deleteUser(@PathVariable Long userId) {
        UserDto res = userService.deleteUserById(userId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/all-users")
    public ResponseEntity<Object> getAllUser() {
        List<UserDto> res = userService.getAllUsers();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
