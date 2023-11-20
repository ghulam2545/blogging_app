package com.ghulam.utils;

import com.ghulam.dtos.CategoryDto;
import com.ghulam.dtos.PostDto;
import com.ghulam.dtos.UserDto;
import com.ghulam.models.Category;
import com.ghulam.models.Post;
import com.ghulam.models.User;

public class ModelMapper {
    public static UserDto userToDto(User user) {
        UserDto dto = new UserDto();

        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setIntro(user.getIntro());
        return dto;
    }

    public static User dtoToUser(UserDto userDto) {
        User user = new User();

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setIntro(userDto.getIntro());
        return user;
    }

    public static CategoryDto categoryToDto(Category category) {
        CategoryDto dto = new CategoryDto();

        dto.setCategoryType(category.getCategoryType());
        return dto;
    }

    public static Category dtoToCategory(CategoryDto categoryDto) {
        Category category = new Category();

        category.setCategoryType(categoryDto.getCategoryType());
        return category;
    }

    public static PostDto postToDto(Post post) {
        PostDto dto  = new PostDto();

        dto.setTitle(post.getTitle());
        dto.setAuthor(post.getAuthor());
        dto.setImageUrl(post.getImageUrl());
        dto.setContent(post.getContent());
        dto.setCategoryId(post.getCategory().getCategoryId());
        dto.setUserId(post.getUser().getUserId());
        return dto;
    }

    public static Post dtoToPost(PostDto postDto) {
        Post post = new Post();

        post.setTitle(postDto.getTitle());
        post.setAuthor(postDto.getAuthor());
        post.setImageUrl(postDto.getImageUrl());
        post.setContent(postDto.getContent());
        return post;
    }
}
