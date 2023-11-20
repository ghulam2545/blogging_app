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
import com.ghulam.utils.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepo postRepo;
    private final CategoryRepo catRepo;
    private final UserRepo userRepo;

    public PostServiceImpl(PostRepo postRepo, CategoryRepo catRepo, UserRepo userRepo) {
        this.postRepo = postRepo;
        this.catRepo = catRepo;
        this.userRepo = userRepo;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        long catId = postDto.getCategoryId();
        long userId = postDto.getUserId();
        Category theCategory = catRepo.findById(catId)
                .orElseThrow(() -> new CategoryNotFoundException("Category id: " + catId + " is not found."));
        User theUser = userRepo.findById(postDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User id: " + userId + " is not found."));
        Post ourPost = dtoToPost(postDto);

        ourPost.setPublishDate(LocalDate.now());
        ourPost.setCategory(theCategory);
        ourPost.setUser(theUser);

        Post data = postRepo.save(ourPost);
        return postToDto(data);
    }

    @Override
    public PostDto getPostById(long postId) {
        Post data = postRepo.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post id: " + postId + " is not found."));
        return postToDto(data);
    }

    @Override
    public PostDto updatePostById(PostDto postDto, long postId) {
        Post updatedPost;
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post id: " + postId + " is not found."));

        long catId = postDto.getCategoryId();
        Category theCategory = catRepo.findById(catId)
                .orElseThrow(() -> new CategoryNotFoundException("Category id: " + catId + " is not found."));

        // update post
        post.setTitle(postDto.getTitle());
        post.setAuthor(postDto.getAuthor());
        post.setImageUrl(postDto.getImageUrl());
        post.setContent(postDto.getContent());
        post.setCategory(theCategory);
        // same user will update the post

        updatedPost = postRepo.save(post);
        return postToDto(updatedPost);
    }

    @Override
    public PostDto deletePostById(long postId) {
        Post data = postRepo.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post id: " + postId + " is not found."));
        postRepo.deleteById(postId);
        return postToDto(data);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> data = postRepo.findAll();
        return data.stream().map(this::postToDto).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostsByUser(long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User id: " + userId + " is not found."));

        List<Post> posts = postRepo.findByUser(user);
        return posts.stream().map(this::postToDto).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostsByCategory(long categoryId) {
        Category category = catRepo.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category id: " + categoryId + " is not found."));

        List<Post> posts = postRepo.findByCategory(category);
        return posts.stream().map(this::postToDto).collect(Collectors.toList());
    }

    private Post dtoToPost(PostDto postDto) {
        return ModelMapper.dtoToPost(postDto);
    }

    private PostDto postToDto(Post post) {
        return ModelMapper.postToDto(post);
    }
}
