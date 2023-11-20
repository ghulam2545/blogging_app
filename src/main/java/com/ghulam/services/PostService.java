package com.ghulam.services;

import com.ghulam.dtos.PostDto;

import java.util.List;

public interface PostService {
    /* CRUD */
    PostDto createPost(PostDto postDto);
    PostDto getPostById(long postId);
    PostDto updatePostById(PostDto postDto, long postId);
    PostDto deletePostById(long postId);

    List<PostDto> getAllPosts();
    List<PostDto> getAllPostsByUser(long userId);
    List<PostDto> getAllPostsByCategory(long categoryId);

    // more
}
