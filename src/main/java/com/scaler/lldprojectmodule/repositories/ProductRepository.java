//package repositories;
package com.scaler.lldprojectmodule.repositories;

import com.scaler.lldprojectmodule.ProductInfo;
import com.scaler.lldprojectmodule.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByTitleContaining(String str);
    @Query("select p.title as title, p.description as description from Product p where p.id = 2")
    List<ProductInfo> doSomething();
    @Query(value = "select * from productservice.product p where p.description like '%best%' ", nativeQuery = true)
    List<Product> doSomething2();

    @Query(value = "select * from productservice.product p where id = :id ", nativeQuery = true)
    List<Product> doSomething3(@Param("id") Long id);

}
