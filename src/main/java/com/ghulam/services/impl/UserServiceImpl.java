package com.ghulam.services.impl;

import com.ghulam.dtos.UserDto;
import com.ghulam.exceptions.UserNotFoundException;
import com.ghulam.models.User;
import com.ghulam.repositories.UserRepo;
import com.ghulam.services.UserService;
import com.ghulam.utils.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User ourUser = dtoToUser(userDto);
        User data = userRepo.save(ourUser);
        return userToDto(data);
    }

    @Override
    public UserDto getUserById(long userId) {
        User data = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User id: " + userId + " is not found."));
        return userToDto(data);
    }

    @Override
    public UserDto updateUserById(UserDto userDto, long userId) {
        User updatedUser;
        User data = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User id: " + userId + " is not found."));

        // update user's data
        data.setFirstName(userDto.getFirstName());
        data.setLastName(userDto.getLastName());
        data.setUsername(userDto.getUsername());
        data.setEmail(userDto.getEmail());
        data.setPassword(userDto.getPassword());
        data.setIntro(userDto.getIntro());

        updatedUser = userRepo.save(data);
        return userToDto(updatedUser);
    }

    @Override
    public UserDto deleteUserById(long userId) {
        User data = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User id: " + userId + " is not found."));
        userRepo.deleteById(userId);
        return userToDto(data);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> data = userRepo.findAll();
        return data.stream().map(this::userToDto).collect(Collectors.toList());
    }

    private User dtoToUser(UserDto userDto) {
        return ModelMapper.dtoToUser(userDto);
    }

    private UserDto userToDto(User user) {
        return ModelMapper.userToDto(user);
    }
}
