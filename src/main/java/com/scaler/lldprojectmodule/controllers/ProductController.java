package com.scaler.lldprojectmodule.controllers;


import com.scaler.lldprojectmodule.commons.AuthenticationCommons;
import com.scaler.lldprojectmodule.dtos.Role;
import com.scaler.lldprojectmodule.dtos.UserDto;
import com.scaler.lldprojectmodule.exceptions.CategoryNotFoundException;
import com.scaler.lldprojectmodule.exceptions.ProductNotFoundException;
import com.scaler.lldprojectmodule.models.Product;
import com.scaler.lldprojectmodule.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private AuthenticationCommons authenticationCommons;
    @Autowired
    public ProductController(@Qualifier("selfProductService") ProductService productService, AuthenticationCommons authenticationCommons) {
        this.productService = productService;
        this.authenticationCommons = authenticationCommons;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) throws Exception {
        return productService.updateProduct(id, product);
    }
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestHeader ("AuthenticationToken") String token) {
        UserDto userDto = authenticationCommons.validateToken(token);
        if(userDto == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        boolean isAdmin = false;
        for(Role role : userDto.getRoles()) {
            if(role.getName().equals("ADMIN")) {
                isAdmin = true;
                break;
            }
        }
        if(!isAdmin) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product with id: "+id+" deleted successfully.", HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) throws CategoryNotFoundException, ProductNotFoundException {
        return productService.replaceProduct(id, product);
    }

}
