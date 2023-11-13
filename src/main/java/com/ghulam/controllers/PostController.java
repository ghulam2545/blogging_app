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
        return null;
    }

    @PutMapping("/update-post/{postId}")
    public ResponseEntity<Object> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable Long postId) {
        return null;
    }

    @DeleteMapping("/delete-post/{postId}")
    public ResponseEntity<Object> deletePost(@PathVariable Long postId) {
        return null;
    }

    @GetMapping("/all-posts")
    public ResponseEntity<Object> getAllPosts() {
        return null;
    }
}
