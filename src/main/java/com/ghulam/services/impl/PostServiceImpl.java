package com.ghulam.services.impl;

import com.ghulam.dtos.PostDto;
import com.ghulam.exceptions.CategoryNotFoundException;
import com.ghulam.exceptions.PostNotFoundException;
import com.ghulam.exceptions.UserNotFoundException;
import com.ghulam.models.Category;
import com.ghulam.models.Post;
import com.ghulam.models.User;
import com.ghulam.repositories.CategoryRepo;
import com.ghulam.repositories.PostRepo;
import com.ghulam.repositories.UserRepo;
import com.ghulam.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepo postRepo;
    private final CategoryRepo catRepo;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    public PostServiceImpl(PostRepo postRepo, CategoryRepo catRepo, UserRepo userRepo, ModelMapper modelMapper) {
        this.postRepo = postRepo;
        this.catRepo = catRepo;
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }


    @Override
    public PostDto createPost(PostDto postDto) {
        long catId = postDto.getCategoryId();
        long userId = postDto.getUserId();
        Category whichCategory = catRepo.findById(catId).orElseThrow(() -> new CategoryNotFoundException("Category id: " + catId + " is not found."));
        User whichUser = userRepo.findById(postDto.getUserId()).orElseThrow(() -> new UserNotFoundException("User id: " + userId + " is not found."));
        Post ourPost = toPost(postDto);

        ourPost.setPublishDate(LocalDate.now());
        ourPost.setCategory(whichCategory);
        ourPost.setUser(whichUser);

        Post data = postRepo.save(ourPost);
        return toDto(data);
    }

    @Override
    public PostDto getPostById(long postId) {
//        try {
//            Optional<Post> post = postRepo.findById(postId);
//            Post data = post.get();
//            return toDto(data);
//        } catch (Exception ex) {
//            throw new PostNotFoundException("Post id: " + postId + " is not found.");
//        }
        return null;
    }

    @Override
    public PostDto updatePostById(PostDto postDto, long postId) {
//        Post updatedPost;
//        try {
//            Optional<Post> post = postRepo.findById(postId);
//            Post data = post.get();
//
//            //  update post's data
//            data.setTitle(postDto.getTitle());
//            data.setAuthor(postDto.getAuthor());
//            data.setPublishDate(LocalDate.now());
//            data.setImageUrl(postDto.getImageUrl());
//            data.setContent(postDto.getContent());
//
//            updatedPost = postRepo.save(data);
//        } catch (Exception ex) {
//            throw new PostNotFoundException("Post id: " + postId + " is not found.");
//        }
//        return toDto(updatedPost);
        return null;
    }

    @Override
    public PostDto deletePostById(long postId) {
//        try {
//            Optional<Post> post = postRepo.findById(postId);
//            Post data = post.get();
//            postRepo.deleteById(postId);
//            return toDto(data);
//        } catch (Exception ex) {
//            throw new PostNotFoundException("Post id: " + postId + " is not found.");
//        }
        return null;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> data = postRepo.findAll();
        return data.stream().map(this::toDto).collect(Collectors.toList());
    }

    private Post toPost(PostDto postDto) {
        return modelMapper.map(postDto, Post.class);
    }

    private PostDto toDto(Post post) {
        return modelMapper.map(post, PostDto.class);
    }
}
