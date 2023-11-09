package com.ghulam.services;

import com.ghulam.dtos.UserDto;

import java.util.List;

public interface UserServiceInterface {
    /**
     * primarily CRUD operation
     */
    UserDto createUser(UserDto userDto);
    UserDto getUserById(long userId);
    UserDto updateUserById(UserDto userDto, long userId);
    UserDto deleteUserById(long userId);

    List<UserDto> getAllUsers();
}
