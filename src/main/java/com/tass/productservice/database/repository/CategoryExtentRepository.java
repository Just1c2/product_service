package com.tass.productservice.database.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tass.productservice.model.response.SearchCategoryResponse;
import com.tass.productservice.database.entities.Category;

@Repository
public interface CategoryExtentRepository {
    void searchCategory(Integer isRoot, String name, Integer page, Integer pageSize, SearchCategoryResponse.Data data);

    void searchCategoryRelation(Integer isRoot, String name, Integer page, Integer pageSize, SearchCategoryResponse.Data data);

    void searchCategoryRelation2(Integer isRoot, String name, Integer page, Integer pageSize, SearchCategoryResponse.Data data);

    List<Category> findAll(String query);

}
