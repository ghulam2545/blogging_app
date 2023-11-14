package com.ghulam.services.impl;

import com.ghulam.dtos.CategoryDto;
import com.ghulam.models.Category;
import com.ghulam.exceptions.CategoryNotFoundException;
import com.ghulam.repositories.CategoryRepo;
import com.ghulam.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo catRepo;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepo catRepo, ModelMapper modelMapper) {
        this.catRepo = catRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category ourUser = toCategory(categoryDto);
        Category data = catRepo.save(ourUser);
        return toDto(data);
    }

    @Override
    public CategoryDto getCategoryById(long categoryId) {
        Category data = catRepo.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category id: " + categoryId + " is not found"));
        return toDto(data);
    }

    @Override
    public CategoryDto updateCategoryById(CategoryDto categoryDto, long categoryId) {
        Category updatedCategory;
        Category data = catRepo.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category id: " + categoryId + " is not found"));

        // update category's data
        data.setCategoryType(categoryDto.getCategoryType());
        updatedCategory = catRepo.save(data);

        return toDto(updatedCategory);
    }

    @Override
    public CategoryDto deleteCategoryById(long categoryId) {
        Category data = catRepo.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category id: " + categoryId + " is not found."));
        catRepo.deleteById(categoryId);
        return toDto(data);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> data = catRepo.findAll();
        return data.stream().map(this::toDto).collect(Collectors.toList());
    }

    private Category toCategory(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }

    private CategoryDto toDto(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }
}
