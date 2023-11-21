package com.ghulam.services.impl;

import com.ghulam.dtos.CommentDto;
import com.ghulam.exceptions.CommentNotFoundException;
import com.ghulam.models.Comment;
import com.ghulam.repositories.CommentRepo;
import com.ghulam.services.CommentService;
import com.ghulam.utils.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepo comRepo;

    public CommentServiceImpl(CommentRepo comRepo) {
        this.comRepo = comRepo;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto) {
        /* todo */
//        Comment ourComment = dtoToComment(commentDto);
//        Comment data = comRepo.save(ourComment);
//        return commentToDto(data);
        return null;
    }

    @Override
    public CommentDto getCommentById(long commentId) {
        /* todo */
//        Comment data = comRepo.findById(commentId)
//                .orElseThrow(() -> new CommentNotFoundException("Comment id: " + commentId + " is not found."));
//        return commentToDto(data);
        return null;
    }

    @Override
    public CommentDto updateCommentById(CommentDto commentDto, long commentId) {
        /* todo */
//        Comment updatedComment;
//        Comment data = comRepo.findById(commentId)
//                .orElseThrow(() -> new CommentNotFoundException("Comment id: " + commentId + " is not found."));
//
//        // update comment's data
//        data.setContent(commentDto.getContent());
//
//        updatedComment = comRepo.save(data);
//        return commentToDto(updatedComment);
        return null;
    }

    @Override
    public CommentDto deleteCommentById(long commentId) {
        /* todo */
//        Comment data = comRepo.findById(commentId)
//                .orElseThrow(() -> new CommentNotFoundException("Comment id: " + commentId + " is not found."));
//        comRepo.deleteById(commentId);
//        return commentToDto(data);
        return null;
    }

    private Comment dtoToComment(CommentDto commentDto) {
        return ModelMapper.dtoToComment(commentDto);
    }

    private CommentDto commentToDto(Comment comment) {
        return ModelMapper.commentToDto(comment);
    }
}
