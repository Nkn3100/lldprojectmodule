package com.scaler.lldprojectmodule.services;

import com.scaler.lldprojectmodule.exceptions.CategoryNotFoundException;
import com.scaler.lldprojectmodule.models.Category;
import com.scaler.lldprojectmodule.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelfCategoryService implements CategoryService{
    CategoryRepository categoryRepository;
    @Autowired
    public SelfCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Category getCategory(Long id) throws CategoryNotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isEmpty()){
            throw new CategoryNotFoundException("Category with id: "+id+" doesn't exist.");
        }
        return categoryOptional.get();
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) throws CategoryNotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isEmpty()){
            throw new CategoryNotFoundException("Category with id: "+id+" doesn't exist.");
        }
        Category categoryFromDB = categoryOptional.get();
        if(category.getName()!=null && !category.getName().equals(categoryFromDB.getName())){
            categoryFromDB.setName(category.getName());
        }
        return categoryRepository.save(categoryFromDB);
    }

    @Override
    public boolean deleteCategory(Long id) throws CategoryNotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isPresent()){
            categoryRepository.delete(categoryOptional.get());
            return true;
        }else {
            throw new CategoryNotFoundException("Category with id: " + id + " doesn't exist.");
        }
    }

    @Override
    public Category replaceCategory(Long id, Category category) throws CategoryNotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isPresent()){
            category.setId(id);
            return categoryRepository.save(category);
        }else {
            throw new CategoryNotFoundException("Category with id: " + id + " doesn't exist.");
        }
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
