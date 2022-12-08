package com.tass.productservice.database.repository;

import com.tass.productservice.model.response.SearchCategoryResponse;

public interface CategoryExtentRepository {
    void searchCategory(Integer isRoot, String name, Integer page, Integer pageSize, SearchCategoryResponse.Data data);

    void searchCategoryRelation(Integer isRoot, String name, Integer page, Integer pageSize, SearchCategoryResponse.Data data);

    void searchCategoryRelation2(Integer isRoot, String name, Integer page, Integer pageSize, SearchCategoryResponse.Data data);

}
