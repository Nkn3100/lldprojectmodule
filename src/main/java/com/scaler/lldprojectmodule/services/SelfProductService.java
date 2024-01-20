package com.scaler.lldprojectmodule.services;

import com.scaler.lldprojectmodule.exceptions.CategoryNotFoundException;
import com.scaler.lldprojectmodule.exceptions.ProductNotFoundException;
import com.scaler.lldprojectmodule.models.Category;
import com.scaler.lldprojectmodule.models.Product;
import com.scaler.lldprojectmodule.repositories.CategoryRepository;
import com.scaler.lldprojectmodule.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProduct(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product with id: "+id+" doesn't exist.");
        }
        return productOptional.get();
    }

    @Override
    @Transactional
    public Product createProduct(Product product) {
        Optional<Category> categoryOptional = categoryRepository.findById(product.getCategory().getId());
        if(categoryOptional.isEmpty()){
//            System.out.println("Category doesn't exist. Creating new category.");
//            categoryRepository.save(product.getCategory());
        }
        return productRepository.save(product);

    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException, CategoryNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product with id: "+id+" doesn't exist.");
        }
        Product productFromDB = productOptional.get();
        if(product.getTitle()!=null && !product.getTitle().equals(productFromDB.getTitle())){
            productFromDB.setTitle(product.getTitle());
        }
        if(product.getPrice()!=null && !product.getPrice().equals(productFromDB.getPrice())){
            productFromDB.setPrice(product.getPrice());
        }
        if (product.getImageUrl()!=null && !product.getImageUrl().equals(productFromDB.getImageUrl())){
            productFromDB.setImageUrl(product.getImageUrl());
        }
        if(product.getDescription()!=null && !product.getDescription().equals(productFromDB.getDescription())){
            productFromDB.setDescription(product.getDescription());
        }
        if(product.getCategory()!=null && !product.getCategory().equals(productFromDB.getCategory())){
            Optional<Category> categoryOptional1 = categoryRepository.findById(product.getCategory().getId());
            if(categoryOptional1.isEmpty()){
                throw new CategoryNotFoundException("Category doesn't exist.");
            }else{
                productFromDB.setCategory(categoryOptional1.get());
            }
        }
        return productRepository.save(productFromDB);

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public boolean deleteProduct(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product with id: "+id+" doesn't exist.");
        }
        productRepository.deleteById(id);
        return true;

    }

    @Override
    public Product replaceProduct(Long id, Product product) throws ProductNotFoundException, CategoryNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product with id: "+id+" doesn't exist.");
        }
        Product productFromDB = productOptional.get();
        productFromDB.setTitle(product.getTitle());
        productFromDB.setPrice(product.getPrice());
        productFromDB.setImageUrl(product.getImageUrl());
        productFromDB.setDescription(product.getDescription());
        Optional<Category> categoryOptional = categoryRepository.findById(product.getCategory().getId());
        if(categoryOptional.isEmpty()){
            throw new CategoryNotFoundException("Category doesn't exist.");
        }else{
            productFromDB.setCategory(categoryOptional.get());
        }
        productFromDB.setCategory(product.getCategory());
        return productRepository.save(productFromDB);
    }
}
