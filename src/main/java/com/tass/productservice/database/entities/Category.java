package com.tass.productservice.database.entities;

import com.fasterxml.jackson.annotation.*;
import com.tass.productservice.utils.Constant;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String icon;
    @JsonProperty("is_root")
    private int isRoot;

    // @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // @JoinTable(
    //     name = "category_relationship",
    //     joinColumns = @JoinColumn(name = "id"),
    //     inverseJoinColumns = @JoinColumn(name = "link_id")
    // )
    // @JsonBackReference
    // private Set<Category> categories;

    // @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // @JsonBackReference
    // private Set<Category> parentCategories;

    public boolean checkIsRoot(){
        return isRoot == Constant.ONOFF.ON;
    }

}
