package com.restorant_springdemo.restorant.controller;

import com.restorant_springdemo.restorant.dto.CategoryDto;
import com.restorant_springdemo.restorant.service.CategoryService;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/categories")

public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public List<CategoryDto>getAllCategories(){
        return categoryService.getAllCategory();
    }

    @PostMapping
    public CategoryDto saveCategory(@Valid @RequestBody CategoryDto categoryDto) throws SystemException {
        return categoryService.saveCategory(categoryDto);
    }


    @PostMapping("/list")
    public List<CategoryDto>saveCategoryList(@Valid @RequestBody List<CategoryDto>categoryDtos) throws SystemException {
        return categoryService.saveCategoryList(categoryDtos);
    }


    @PutMapping
    public CategoryDto updateCategory (@Valid @RequestBody CategoryDto categoryDtos) throws SystemException {
        return categoryService.updateCategory(categoryDtos);
    }


    @PutMapping("/list")
    public List<CategoryDto>updateCategoryList(@Valid @RequestBody List<CategoryDto>categoryDtos) throws SystemException {
        return categoryService.updateCategoryList(categoryDtos);
    }

    @DeleteMapping
    public void deleteCategoryById(@Valid @RequestParam Long id) throws SystemException {
      categoryService.deleteCategoryById(id);
    }



    @DeleteMapping("/list")
    public void deleteCategoryListByIds(@Valid @RequestParam List <Long> ids) throws SystemException {
        categoryService.deleteCategoryListByIds(ids);
    }


}
