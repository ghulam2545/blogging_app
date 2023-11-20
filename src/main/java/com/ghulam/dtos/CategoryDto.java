package com.ghulam.dtos;

import com.ghulam.enums.CategoryType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryDto {
    @NotNull
    private CategoryType categoryType;
}
