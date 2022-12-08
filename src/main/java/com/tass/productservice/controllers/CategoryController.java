package com.tass.productservice.controllers;

import com.tass.productservice.model.ApiException;
import com.tass.productservice.model.BaseResponse;
import com.tass.productservice.model.request.CategoryRequest;
import com.tass.productservice.model.response.SearchCategoryResponse;
import com.tass.productservice.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController{

    @Autowired
    CategoryService categoryService;

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable Long id)throws
        ApiException {
        return createdResponse(categoryService.deleteCategory(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> edit(@PathVariable Long id , @RequestBody CategoryRequest request)throws
        ApiException {
        return createdResponse(categoryService.editCategory(id , request));
    }
    
    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CategoryRequest request)throws
        ApiException {
        return createdResponse(categoryService.createCategory(request));
    }

    @GetMapping
    public SearchCategoryResponse search(@RequestParam(name = "is_root" , required = false) Integer isRoot , @RequestParam(required = false) String name,
        @RequestParam(required = false) Integer page , @RequestParam(name = "page_size" , required = false) Integer pageSize){
        return categoryService.search(isRoot, name, page, pageSize);
    }

    @GetMapping("/getrelation")
    public SearchCategoryResponse searchRelation(@RequestParam(name = "is_root" , required = false) Integer isRoot , @RequestParam(required = false) String name, @RequestParam(required = false) Integer page , @RequestParam(name = "page_size" , required = false) Integer pageSize){
        return categoryService.searchRelation(isRoot, name, page, pageSize);
    }

    @GetMapping("/getrelation2")
    public SearchCategoryResponse searchRelation2(@RequestParam(name = "is_root" , required = false) Integer isRoot , @RequestParam(required = false) String name, @RequestParam(required = false) Integer page , @RequestParam(name = "page_size" , required = false) Integer pageSize){
        return categoryService.searchRelation2(isRoot, name, page, pageSize);
    }

    @GetMapping("/getchildren/{id}")
    public ResponseEntity<BaseResponse> findChildren(@PathVariable Long id){
        return createdResponse(categoryService.findChildren(id));
    } 

    @GetMapping("/getparent/{id}")
    public ResponseEntity<BaseResponse> findParent(@PathVariable Long id){
        return createdResponse(categoryService.findParent(id));
    } 
    @GetMapping("/getchildren2/{id}")
    public ResponseEntity<BaseResponse> findChildrenByJoin(@PathVariable Long id){
        return createdResponse(categoryService.findChildrenByJoin(id));
    }
    @GetMapping("/getparent2/{id}")
    public ResponseEntity<BaseResponse> findParentByJoin(@PathVariable Long id){
        return createdResponse(categoryService.findParentByJoin(id));
    }
}
