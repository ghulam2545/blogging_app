package com.ghulam.controllers;

import com.ghulam.dtos.CategoryDto;
import com.ghulam.response.Response;
import com.ghulam.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService catService;

    private ResponseEntity<Object> response(String message, Object data) {
        return Response.builder(message, HttpStatus.OK, data);
    }

    public CategoryController(CategoryService catService) {
        this.catService = catService;
    }

    @PostMapping("/add-category")
    public ResponseEntity<Object> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto result = catService.createCategory(categoryDto);
        return response("Added a new category", result);
    }

    @GetMapping("/get-category/{categoryId}")
    public ResponseEntity<Object> readCategory(@PathVariable Long categoryId) {
        CategoryDto result = catService.getCategoryById(categoryId);
        return response("Read a category by id: " + categoryId, result);
    }

    @PutMapping("/update-category/{categoryId}")
    public ResponseEntity<Object> updateCategory(@RequestBody CategoryDto categoryDto,
            @PathVariable Long categoryId) {
        CategoryDto result = catService.updateCategoryById(categoryDto, categoryId);
        return response("Updated a category by id: " + categoryId, result);
    }

    @DeleteMapping("/delete-category/{categoryId}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long categoryId) {
        CategoryDto result = catService.deleteCategoryById(categoryId);
        return response("Deleted a category by id: " + categoryId, result);
    }

    @GetMapping("/all-categories")
    public ResponseEntity<Object> getAllCategories() {
        List<CategoryDto> result = catService.getAllCategories();
        return response("Getting all categories", result);
    }
}
