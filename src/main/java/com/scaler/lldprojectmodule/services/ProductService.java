package com.scaler.lldprojectmodule.services;


import com.scaler.lldprojectmodule.models.Product;

import java.util.List;

public interface ProductService {

    Product getProduct(Long id);

    Product createProduct(Product product);

    String updateProduct(Long id, Product product);

    List<Product> getAllProducts();

    Product deleteProduct(Long id);

    Product replaceProduct(Long id, Product product);

}
