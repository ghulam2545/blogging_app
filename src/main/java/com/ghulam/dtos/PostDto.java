package com.ghulam.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {
    @NotEmpty
    @Size(min = 3, max = 100, message = "title must be of size (3-100)")
    private String title;

    @NotEmpty
    @Size(min = 3, max = 40, message = "author name must be of size (3-40)")
    private String author;

    private String imageUrl = "sample.png";

    @NotEmpty
    @Size(min = 20, max = 1000, message = "content must be of size (200-1000)")
    private String content;

    private long categoryId;

    private long userId;
}
