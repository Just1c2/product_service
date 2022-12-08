package com.tass.productservice.database.repository.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.tass.productservice.database.entities.Category;
import com.tass.productservice.database.repository.CategoryExtentRepository;
import com.tass.productservice.model.dto.CategoryInfo;
import com.tass.productservice.model.response.SearchCategoryResponse;

public class CategoryExtentRepositoryImpl implements CategoryExtentRepository {
   @PersistenceContext
   Session session;

   public void searchCategory(Integer isRoot, String name, Integer page, Integer pageSize, SearchCategoryResponse.Data data){
    StringBuilder baseSql = new StringBuilder();

    baseSql.append("FROM com.tass.productservice.database.entities.Category c WHERE 1 = 1");

    if(isRoot != null){
        baseSql.append(" AND c.isRoot = ").append(isRoot);
    }
    if(StringUtils.isNotBlank(name)){
        baseSql.append(" AND c.name LIKE '%").append(name).append("%'");
    }

    //select total item

    String sqlCount = "SELECT count(c) " + baseSql;

    Query query = session.createQuery(sqlCount);

    Object totalItemValue = query.getSingleResult();

    if(totalItemValue instanceof BigInteger){
        BigInteger totalItem = (BigInteger) totalItemValue;

        data.setTotalItem(totalItem.longValue());
    }
    else if (totalItemValue instanceof Number){
        data.setTotalItem((Long) totalItemValue);
    }

    //select item info
    if (data.getTotalItem() > 0) {

        String querySql =
            "SELECT new com.tass.productservice.model.dto.CategoryInfo(c.id, c.name, c.icon, c.description, c.isRoot) " +
                baseSql;
        Query queryItem = session.createQuery(querySql, CategoryInfo.class);


        page--;
        int fistResult = page * pageSize;
        query.setMaxResults(pageSize).setFirstResult(fistResult);

        List<CategoryInfo> categoryList = queryItem.getResultList();

        data.setItems(categoryList);

    } else {
        data.setItems(new ArrayList<>());
    }
   }

   public void searchCategoryRelation(Integer isRoot, String name, Integer page, Integer pageSize, SearchCategoryResponse.Data data){
        StringBuilder mainSql = new StringBuilder();

        mainSql.append("FROM category c WHERE 1 = 1 ");

        if(isRoot != null){
            mainSql.append(" AND c.isRoot = ").append(isRoot);
        }
        if(StringUtils.isNotBlank(name)){
            mainSql.append(" AND c.name LIKE '%").append(name).append("%'");
        }

        String sqlCount = "SELECT count(*) " + mainSql;

        NativeQuery query = session.createNativeQuery(sqlCount);

        Object totalItemValue = query.getSingleResult();


        if(totalItemValue instanceof BigInteger){
            BigInteger totalItem = (BigInteger) totalItemValue;
    
            data.setTotalItem(totalItem.longValue());
        }
        else if (totalItemValue instanceof Number){
            data.setTotalItem((Long) totalItemValue);
        }

        if (data.getTotalItem() > 0) {

            String querySql =
                "SELECT c.id, c.name, c.icon, c.description, c.is_root, (select JSON_ARRAYAGG(JSON_OBJECT('id', d.id, 'name', d.name, 'icon', d.icon, 'description', d.description, 'is_root', d.is_root)) from category d where d.id in (select cr.link_id from category_relationship cr where cr.id = c.id)) as children, (select JSON_ARRAYAGG(JSON_OBJECT('id', e.id, 'name', e.name, 'icon', e.icon, 'description', e.description, 'is_root', e.is_root)) from category e where e.id in (select cr.id from category_relationship cr where cr.link_id = c.id)) as parents " +
                    mainSql;
            NativeQuery queryItem = session.createNativeQuery(querySql);
    
    
            page--;
            int fistResult = page * pageSize;
            query.setMaxResults(pageSize).setFirstResult(fistResult);
    
            List<CategoryInfo> categoryList = queryItem.getResultList();
    
            data.setItems(categoryList);
    
        } else {
            data.setItems(new ArrayList<>());
        }
   }

    public void searchCategoryRelation2(Integer isRoot, String name, Integer page, Integer pageSize, SearchCategoryResponse.Data data){

        StringBuilder baseSql = new StringBuilder();

        baseSql.append("FROM c_p WHERE 1 = 1");

        if(StringUtils.isNotBlank(name)){
            baseSql.append(" AND name LIKE '%").append(name).append("%'");
        }

        String sqlCount = "SELECT count(*) " + baseSql;

        NativeQuery query = session.createNativeQuery(sqlCount);

        Object totalItemValue = query.getSingleResult();

        if(totalItemValue instanceof BigInteger){
            BigInteger totalItem = (BigInteger) totalItemValue;
    
            data.setTotalItem(totalItem.longValue());
        }
        else if (totalItemValue instanceof Number){
            data.setTotalItem((Long) totalItemValue);
        }

        if(data.getTotalItem() > 0){
            String getViewValueQuery = "SELECT * " + baseSql;
            NativeQuery getViewSql = session.createNativeQuery(getViewValueQuery);

            page--;
            int fistResult = page * pageSize;
            query.setMaxResults(pageSize).setFirstResult(fistResult);
    
            List<CategoryInfo> categoryList = getViewSql.getResultList();
    
            data.setItems(categoryList);
        }
        else {
            data.setItems(new ArrayList<>());
        }
    }

    @Override
    public List<Category> findAll(String query) {
        return session.createNativeQuery(query, Category.class).getResultList();
    }
    
}
