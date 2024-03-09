package com.scaler.lldprojectmodule;

import com.scaler.lldprojectmodule.models.Category;
import com.scaler.lldprojectmodule.models.Product;
import com.scaler.lldprojectmodule.repositories.CategoryRepository;
import com.scaler.lldprojectmodule.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductServiceApplicationTests {
//    @Autowired
//    ProductRepository productRepository;
//    @Autowired
//    CategoryRepository categoryRepository;

//    @Test
//    void contextLoads() {
//    }
//    @Test
//    @Transactional
//    void testQueries() {
//        List<Product> products = productRepository.findByTitleContaining("naman");
//        List<ProductInfo> products1 = productRepository.doSomething();
//        for(ProductInfo p : products1){
//            System.out.println(p.getTitle());
//            System.out.println(p.getDescription());
//        }
//        List<Product> products3 = productRepository.doSomething2();
//        List<Product> product4 =  productRepository.doSomething3(1L);
//        System.out.println("--------------------");
//
//        Optional<Category> category = categoryRepository.findById(1L);
//        if (category.isPresent()) {
//            System.out.println("category name : "+category.get().getName());
//
//            for(Product product : category.get().getProducts()){
//                System.out.println("product title : "+product.getTitle());
//            }
//        }
//
//    }


}
