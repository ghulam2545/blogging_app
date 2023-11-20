package com.ghulam.controllers;

import com.ghulam.dtos.PostDto;
import com.ghulam.response.Response;
import com.ghulam.services.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    private ResponseEntity<Object> response(String message, Object data) {
        return Response.builder(message, HttpStatus.OK, data);
    }

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/add-post")
    public ResponseEntity<Object> createPost(@Valid @RequestBody PostDto postDto) {
        PostDto result = postService.createPost(postDto);
        return response("Added a new post", result);
    }

    @GetMapping("/get-post/{postId}")
    public ResponseEntity<Object> readPost(@PathVariable Long postId) {
        PostDto result = postService.getPostById(postId);
        return response("Read a post by id: " + postId, result);
    }

    @PutMapping("/update-post/{postId}")
    public ResponseEntity<Object> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable Long postId) {
        PostDto result = postService.updatePostById(postDto, postId);
        return response("Updated a post by id: " + postId, result);
    }

    @DeleteMapping("/delete-post/{postId}")
    public ResponseEntity<Object> deletePost(@PathVariable Long postId) {
        PostDto result = postService.deletePostById(postId);
        return response("Deleted a post by id: " + postId, result);
    }

    @GetMapping("/all-posts")
    public ResponseEntity<Object> getAllPosts() {
        List<PostDto> result = postService.getAllPosts();
        return response("Getting all posts", result);
    }

    @GetMapping("/all-post-by-user/{userId}")
    public ResponseEntity<Object> getAllPostByUser(@PathVariable Long userId) {
        List<PostDto> result = postService.getAllPostsByUser(userId);
        return response("Getting all post by user id: " + userId, result);
    }

    @GetMapping("/all-post-by-category/{categoryId}")
    public ResponseEntity<Object> getAllPostByCategory(@PathVariable Long categoryId) {
        List<PostDto> result = postService.getAllPostsByCategory(categoryId);
        return response("Getting all post by category id: " + categoryId, result);
    }
}
