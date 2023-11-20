package com.ghulam.services.impl;

import com.ghulam.dtos.CategoryDto;
import com.ghulam.models.Category;
import com.ghulam.exceptions.CategoryNotFoundException;
import com.ghulam.repositories.CategoryRepo;
import com.ghulam.services.CategoryService;
import com.ghulam.utils.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo catRepo;

    public CategoryServiceImpl(CategoryRepo catRepo) {
        this.catRepo = catRepo;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category ourUser = dtoToCategory(categoryDto);
        Category data = catRepo.save(ourUser);
        return categoryToDto(data);
    }

    @Override
    public CategoryDto getCategoryById(long categoryId) {
        Category data = catRepo.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category id: " + categoryId + " is not found"));
        return categoryToDto(data);
    }

    @Override
    public CategoryDto updateCategoryById(CategoryDto categoryDto, long categoryId) {
        Category updatedCategory;
        Category data = catRepo.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category id: " + categoryId + " is not found"));

        // update category's data
        data.setCategoryType(categoryDto.getCategoryType());
        updatedCategory = catRepo.save(data);

        return categoryToDto(updatedCategory);
    }

    @Override
    public CategoryDto deleteCategoryById(long categoryId) {
        Category data = catRepo.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category id: " + categoryId + " is not found."));
        catRepo.deleteById(categoryId);
        return categoryToDto(data);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> data = catRepo.findAll();
        return data.stream().map(this::categoryToDto).collect(Collectors.toList());
    }

    private Category dtoToCategory(CategoryDto categoryDto) {
        return ModelMapper.dtoToCategory(categoryDto);
    }

    private CategoryDto categoryToDto(Category category) {
        return ModelMapper.categoryToDto(category);
    }
}
