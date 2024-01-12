package com.scaler.lldprojectmodule.services;


import com.scaler.lldprojectmodule.exceptions.ProductNotFoundException;
import com.scaler.lldprojectmodule.models.Product;

import java.util.List;

public interface ProductService {

    Product getProduct(Long id) throws ProductNotFoundException;

    Product createProduct(Product product);

    String updateProduct(Long id, Product product) throws Exception;

    List<Product> getAllProducts();

    Product deleteProduct(Long id);

    Product replaceProduct(Long id, Product product);

}
