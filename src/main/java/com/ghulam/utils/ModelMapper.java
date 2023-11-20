package com.ghulam.utils;

import com.ghulam.dtos.PostDto;
import com.ghulam.models.Post;

public class ModelMapper {
    public static PostDto map(Post post) {
        PostDto res = new PostDto();

        res.setTitle(post.getTitle());
        res.setAuthor(post.getAuthor());
        res.setContent(post.getContent());

        return res;
    }

    public static Post map(PostDto postDto) {
        Post res = new Post();

        res.setTitle(postDto.getTitle());
        res.setAuthor(postDto.getAuthor());
        res.setImageUrl(postDto.getImageUrl());
        res.setContent(postDto.getContent());

        return res;
    }
}
