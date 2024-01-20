package com.scaler.lldprojectmodule.services;

import com.scaler.lldprojectmodule.exceptions.CategoryNotFoundException;
import com.scaler.lldprojectmodule.models.Category;

import java.util.List;

public interface CategoryService {
    Category getCategory(Long id) throws CategoryNotFoundException;
    Category createCategory(Category category);
    Category updateCategory(Long id, Category category) throws CategoryNotFoundException;
    boolean deleteCategory(Long id) throws CategoryNotFoundException;
    Category replaceCategory(Long id, Category category) throws CategoryNotFoundException;
    List<Category> getAllCategories();
}
