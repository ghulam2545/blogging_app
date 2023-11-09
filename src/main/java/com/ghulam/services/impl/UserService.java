package com.ghulam.services.impl;

import com.ghulam.dtos.UserDto;
import com.ghulam.exceptions.UserNotFoundException;
import com.ghulam.models.User;
import com.ghulam.repositories.UserRepo;
import com.ghulam.services.UserServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInterface {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    public UserService(UserRepo userRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User ourUser = toUser(userDto);
        User data = userRepo.save(ourUser);
        return toDto(data);
    }

    @Override
    public UserDto getUserById(long userId) {
        try {
            Optional<User> user = userRepo.findById(userId);
                User data = user.get();
                return toDto(data);
        } catch (Exception ex) {
            throw new UserNotFoundException("User id: " + userId + " is not found.");
        }
    }

    @Override
    public UserDto updateUserById(UserDto userDto, long userId) {
        User updatedUser;
        try {
            Optional<User> user = userRepo.findById(userId);
            User data = user.get();

            //  update user's data
            data.setFirstName(userDto.getFirstName());
            data.setLastName(userDto.getLastName());
            data.setUsername(userDto.getUsername());
            data.setEmail(userDto.getEmail());
            data.setPassword(userDto.getPassword());
            data.setIntro(userDto.getIntro());
            
            updatedUser = userRepo.save(data);
        } catch (Exception ex) {
            throw new UserNotFoundException("User id: " + userId + " is not found.");
        }
        return toDto(updatedUser);
    }

    @Override
    public UserDto deleteUserById(long userId) {
        try {
            Optional<User> user = userRepo.findById(userId);
            User data = user.get();
            userRepo.deleteById(userId);
            return toDto(data);
        } catch (Exception ex) {
            throw new UserNotFoundException("User id: " + userId + " is not found.");
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> data = userRepo.findAll();
        return data.stream().map(this::toDto).collect(Collectors.toList());
    }

    private User toUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    private UserDto toDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
