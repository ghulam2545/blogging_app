package com.ghulam.services;

import com.ghulam.dtos.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto userDto);
    CommentDto getCommentById(long commentId);
    CommentDto updateCommentById(CommentDto userDto, long commentId);
    CommentDto deleteCommentById(long commentId);
}
