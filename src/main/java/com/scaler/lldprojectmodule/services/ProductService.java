package com.scaler.lldprojectmodule.services;


import com.scaler.lldprojectmodule.exceptions.CategoryNotFoundException;
import com.scaler.lldprojectmodule.exceptions.ProductNotFoundException;
import com.scaler.lldprojectmodule.models.Product;

import java.util.List;

public interface ProductService {

    Product getProduct(Long id) throws ProductNotFoundException;

    Product createProduct(Product product);

    Product updateProduct(Long id, Product product) throws Exception;

    List<Product> getAllProducts();

    boolean deleteProduct(Long id) throws ProductNotFoundException;

    Product replaceProduct(Long id, Product product) throws ProductNotFoundException, CategoryNotFoundException;

}
