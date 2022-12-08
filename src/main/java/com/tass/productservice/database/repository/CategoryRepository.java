package com.tass.productservice.database.repository;

import com.tass.productservice.database.entities.Category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category , Long> , CategoryExtentRepository{
    List<Category> findByName(String name);

    @Query(value = "SELECT * from category c, category_relationship WHERE c.id = cr.id", nativeQuery = true)
    List<Category> findChildren(Long id);

    @Query(value = "SELECT * from category c, category_relationship WHERE c.id = cr.link_id", nativeQuery = true)
    List<Category> findParent(Long id);

    List<Category> findAll(String query);
}
