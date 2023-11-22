package com.ghulam.services.impl;

import com.ghulam.dtos.CommentDto;
import com.ghulam.exceptions.CommentNotFoundException;
import com.ghulam.exceptions.PostNotFoundException;
import com.ghulam.exceptions.UserNotFoundException;
import com.ghulam.models.Comment;
import com.ghulam.models.Post;
import com.ghulam.models.User;
import com.ghulam.repositories.CommentRepo;
import com.ghulam.repositories.PostRepo;
import com.ghulam.repositories.UserRepo;
import com.ghulam.services.CommentService;
import com.ghulam.utils.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepo comRepo;
    private final PostRepo postRepo;
    private final UserRepo userRepo;

    public CommentServiceImpl(CommentRepo comRepo, PostRepo postRepo, UserRepo userRepo) {
        this.comRepo = comRepo;
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto) {
        long postId = commentDto.getPostId();
        long userId = commentDto.getUserId();

        Post thePost = postRepo.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post id: " + postId + " is not found."));
        User theUser = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User id: " + userId + " is not found."));

        Comment ourComment = dtoToComment(commentDto);

        ourComment.setDate(LocalDate.now());
        ourComment.setPost(thePost);
        ourComment.setUser(theUser);

        Comment data = comRepo.save(ourComment);
        return commentToDto(data);
    }

    @Override
    public CommentDto getCommentById(long commentId) {
        Comment data = comRepo.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment id: " + commentId + " is not found."));
        return commentToDto(data);
    }

    @Override
    public CommentDto updateCommentById(CommentDto commentDto, long commentId) {
        Comment updatedComment;
        Comment comment = comRepo.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment id: " + commentId + " is not found."));

        long postId = commentDto.getPostId();
        Post thePost = postRepo.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post id: " + postId + " is not found."));

        // update comment's data
        comment.setContent(commentDto.getContent());
        comment.setDate(LocalDate.now());
        comment.setPost(thePost);
        // same user will update the post

        updatedComment = comRepo.save(comment);
        return commentToDto(updatedComment);
    }

    @Override
    public CommentDto deleteCommentById(long commentId) {
        Comment data = comRepo.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment id: " + commentId + " is not found."));
        comRepo.deleteById(commentId);
        return commentToDto(data);
    }

    private Comment dtoToComment(CommentDto commentDto) {
        return ModelMapper.dtoToComment(commentDto);
    }

    private CommentDto commentToDto(Comment comment) {
        return ModelMapper.commentToDto(comment);
    }
}
