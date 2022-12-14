package com.tass.productservice.model.dto;

import java.util.List;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CategoryInfo {
    private Long id;
    private String name;
    private String icon;
    private String description;
    @JsonProperty("is_root")
    private Integer isRoot;
    @JsonProperty("parents")
    private List<CategoryInfo> parents;

    @JsonProperty("children")
    private List<CategoryInfo> children;

    public CategoryInfo(Long id, String name, String icon, String description, Integer isRoot){
        this.id = id;
        this.name = name;
        this.description = description;
        this.icon =  icon;
        this.isRoot = isRoot;
        
    }

    public CategoryInfo(){}

    

}
