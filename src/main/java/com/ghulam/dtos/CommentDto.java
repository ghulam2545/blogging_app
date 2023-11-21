package com.ghulam.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CommentDto {
    @NotEmpty
    private String content;

    @NotNull
    private LocalDate date;

    @NotNull
    private long postId;

    @NotNull
    private long userId;
}
