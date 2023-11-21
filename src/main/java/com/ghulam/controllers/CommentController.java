package com.ghulam.controllers;

import com.ghulam.dtos.CommentDto;
import com.ghulam.response.Response;
import com.ghulam.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    private ResponseEntity<Object> response(String message, Object data) {
        return Response.builder(message, HttpStatus.OK, data);
    }

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add-comment")
    public ResponseEntity<Object> createUser(@Valid @RequestBody CommentDto commentDto) {
        CommentDto result = commentService.createComment(commentDto);
        return response("Added a new comment", result);
    }

    @GetMapping("/get-comment/{commentId}")
    public ResponseEntity<Object> readUser(@PathVariable Long commentId) {
        CommentDto result = commentService.getCommentById(commentId);
        return response("Read a comment by id: " + commentId, result);
    }

    @PutMapping("/update-comment/{commentId}")
    public ResponseEntity<Object> updateUser(@Valid @RequestBody CommentDto commentDto, @PathVariable Long commentId) {
        CommentDto result = commentService.updateCommentById(commentDto, commentId);
        return response("Updated a comment by id: " + commentId, result);
    }

    @DeleteMapping("/delete-comment/{commentId}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long commentId) {
        CommentDto result = commentService.deleteCommentById(commentId);
        return response("Deleted a comment by id: " + commentId, result);
    }
}
