package com.scaler.lldprojectmodule.controllers;

import com.scaler.lldprojectmodule.exceptions.CategoryNotFoundException;
import com.scaler.lldprojectmodule.models.Category;
import com.scaler.lldprojectmodule.repositories.CategoryRepository;
import com.scaler.lldprojectmodule.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable("id") Long id) throws CategoryNotFoundException {
        return ResponseEntity.ok(categoryService.getCategory(id));
    }
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.createCategory(category));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @RequestBody Category category) throws CategoryNotFoundException {
        return ResponseEntity.ok(categoryService.updateCategory(id, category));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) throws CategoryNotFoundException {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category with id: "+id+" deleted successfully.");
    }
    @PutMapping("/{id}")
    public ResponseEntity<Category> replaceCategory(@PathVariable("id") Long id, @RequestBody Category category) throws CategoryNotFoundException {
        return ResponseEntity.ok(categoryService.replaceCategory(id, category));
    }


}
