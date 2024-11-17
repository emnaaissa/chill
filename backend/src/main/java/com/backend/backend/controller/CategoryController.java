package com.backend.backend.controller;

import com.backend.backend.model.Category;
import com.backend.backend.model.MenuItem;
import com.backend.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Get all categories with their menu items
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        try {
            List<Category> categories = categoryService.getAllCategories();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (ExecutionException | InterruptedException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get a category by ID with its menu items
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable String id) {
        try {
            Category category = categoryService.getCategoryById(id);
            if (category != null) {
                return new ResponseEntity<>(category, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (ExecutionException | InterruptedException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create a new category
    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        try {
            String response = categoryService.createCategory(category);
            return new ResponseEntity<>("Category created successfully at " + response, HttpStatus.CREATED);
        } catch (ExecutionException | InterruptedException e) {
            return new ResponseEntity<>("Failed to create category", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an existing category
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable String id, @RequestBody Category category) {
        try {
            String response = categoryService.updateCategory(id, category);
            return new ResponseEntity<>("Category updated successfully at " + response, HttpStatus.OK);
        } catch (ExecutionException | InterruptedException e) {
            return new ResponseEntity<>("Failed to update category", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a category by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable String id) {
        try {
            String response = categoryService.deleteCategory(id);
            return new ResponseEntity<>("Category deleted successfully at " + response, HttpStatus.OK);
        } catch (ExecutionException | InterruptedException e) {
            return new ResponseEntity<>("Failed to delete category", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Add a menu item to a category
    @PostMapping("/{categoryId}/menuItems")
    public ResponseEntity<String> addMenuItemToCategory(@PathVariable String categoryId, @RequestBody MenuItem menuItem) {
        try {
            String response = categoryService.addMenuItemToCategory(categoryId, menuItem);
            return new ResponseEntity<>("Menu item added successfully at " + response, HttpStatus.CREATED);
        } catch (ExecutionException | InterruptedException e) {
            return new ResponseEntity<>("Failed to add menu item", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update a menu item of a category
    @PutMapping("/{categoryId}/menuItems/{menuItemId}")
    public ResponseEntity<String> updateMenuItem(@PathVariable String categoryId, @PathVariable String menuItemId, @RequestBody MenuItem menuItem) {
        try {
            String response = categoryService.updateMenuItem(categoryId, menuItemId, menuItem);
            return new ResponseEntity<>("Menu item updated successfully at " + response, HttpStatus.OK);
        } catch (ExecutionException | InterruptedException e) {
            return new ResponseEntity<>("Failed to update menu item", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a menu item from a category
    @DeleteMapping("/{categoryId}/menuItems/{menuItemId}")
    public ResponseEntity<String> deleteMenuItem(@PathVariable String categoryId, @PathVariable String menuItemId) {
        try {
            String response = categoryService.deleteMenuItem(categoryId, menuItemId);
            return new ResponseEntity<>("Menu item deleted successfully at " + response, HttpStatus.OK);
        } catch (ExecutionException | InterruptedException e) {
            return new ResponseEntity<>("Failed to delete menu item", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
