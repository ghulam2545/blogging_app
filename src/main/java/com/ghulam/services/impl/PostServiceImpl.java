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
        Category whichCategory = catRepo.findById(catId)
                .orElseThrow(() -> new CategoryNotFoundException("Category id: " + catId + " is not found."));
        User whichUser = userRepo.findById(postDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User id: " + userId + " is not found."));
        Post ourPost = toPost(postDto);

        ourPost.setPublishDate(LocalDate.now());
        ourPost.setCategory(whichCategory);
        ourPost.setUser(whichUser);

        Post data = postRepo.save(ourPost);
        return toDto(data);
    }

    @Override
    public PostDto getPostById(long postId) {
        Post data = postRepo.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post id: " + postId + " is not found."));
        return toDto(data);
    }

    @Override
    public PostDto updatePostById(PostDto postDto, long postId) {
        /* todos */
        return null;
    }

    @Override
    public PostDto deletePostById(long postId) {
        Post data = postRepo.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post id: " + postId + " is not found."));
        postRepo.deleteById(postId);
        return toDto(data);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> data = postRepo.findAll();
        return data.stream().map(this::toDto).collect(Collectors.toList());
    }

    private Post toPost(PostDto postDto) {
        return ModelMapper.map(postDto);
    }

    private PostDto toDto(Post post) {
        return ModelMapper.map(post);
    }
}
