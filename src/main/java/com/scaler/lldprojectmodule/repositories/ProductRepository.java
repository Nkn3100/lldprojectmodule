//package repositories;
package com.scaler.lldprojectmodule.repositories;

import com.scaler.lldprojectmodule.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
