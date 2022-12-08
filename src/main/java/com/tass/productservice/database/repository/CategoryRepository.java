package com.tass.productservice.database.repository;

import com.tass.productservice.database.entities.Category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category , Long> , CategoryExtentRepository{
    List<Category> findByName(String name);

}
