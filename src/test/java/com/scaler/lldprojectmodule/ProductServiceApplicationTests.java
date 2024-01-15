package com.scaler.lldprojectmodule;

import com.scaler.lldprojectmodule.models.Product;
import com.scaler.lldprojectmodule.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceApplicationTests {
    @Autowired
    ProductRepository productRepository;

    @Test
    void contextLoads() {
    }
    @Test
    @Transactional
    void testQueries() {
        productRepository.findByTitleContaining("naman");

    }


}
