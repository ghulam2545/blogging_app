package com.ghulam.services;

import com.ghulam.dtos.CategoryDto;

import java.util.List;

public interface CategoryService {
    /* CRUD */
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto getCategoryById(long categoryId);
    CategoryDto updateCategoryById(CategoryDto categoryDto, long categoryId);
    CategoryDto deleteCategoryById(long categoryId);

    List<CategoryDto> getAllCategories();
}
