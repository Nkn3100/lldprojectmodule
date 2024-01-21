package com.scaler.lldprojectmodule.repositories;

import com.scaler.lldprojectmodule.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

    Optional<Category> findByNameStartingWith(String naman);

    List<Category> findAllByName(String laptop);
    List<Category> findAllByNameStartingWith(String lap);
}
