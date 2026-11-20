package com.restorant_springdemo.restorant.service.impl;

import com.restorant_springdemo.restorant.dto.CategoryDto;
import com.restorant_springdemo.restorant.mapper.CategoryMapper;
import com.restorant_springdemo.restorant.model.Category;
import com.restorant_springdemo.restorant.repo.CategoryRepo;
import com.restorant_springdemo.restorant.service.CategoryService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryMapper categoryMapper;
    private CategoryRepo categoryRepo;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepo categoryRepo) throws SystemException {
        this.categoryMapper = categoryMapper;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<CategoryDto> getAllCategory() {

        return categoryMapper.toDtoList(categoryRepo.findAll());
    }


    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) throws SystemException {
        if (Objects.nonNull(categoryDto.getId())) {
            throw new SystemException("category.id.mustBeNull");
        }

        if (categoryRepo.existsByNameIgnoreCase(categoryDto.getName())) {
            throw new SystemException("category.name.exists");
        }

        Category category = categoryMapper.toEntity(categoryDto);

        return toDto(categoryRepo.save(category));
    }


    @Override
    public List<CategoryDto> saveCategoryList(List<CategoryDto> categoryDtos) throws SystemException {
        if (Objects.isNull(categoryDtos) || categoryDtos.isEmpty()) {
            throw new SystemException("category.list.notEmpty");
        }

        List<CategoryDto> savedCategories = new ArrayList<>();

        for (CategoryDto categoryDto : categoryDtos) {

            CategoryDto savedCategory = saveCategory(categoryDto);

            savedCategories.add(savedCategory);

        }

        return savedCategories;
    }


    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) throws SystemException {
        if (Objects.isNull(categoryDto.getId())) {
            throw new SystemException("category.id.notNull");
        }

        if (!categoryRepo.existsById(categoryDto.getId())) {
            throw new SystemException("category.notFound");
        }

        if (categoryRepo.existsByNameIgnoreCaseAndIdNot(categoryDto.getName(), categoryDto.getId())) {
            throw new SystemException("category.name.exists");
        }

        Category category = categoryMapper.toEntity(categoryDto);

        Category updateCategory = categoryRepo.save(category);

        return toDto(updateCategory);
    }



    @Override
    public List<CategoryDto> updateCategoryList(List<CategoryDto> categoryDtos) throws SystemException {
        if (Objects.isNull(categoryDtos) || categoryDtos.isEmpty()) {
            throw new SystemException("category.list.notEmpty");
        }

        List<CategoryDto> updatedCategories = new ArrayList<>();

        for (CategoryDto categoryDto : categoryDtos) {
            CategoryDto updatedCategory = updateCategory(categoryDto);
            updatedCategories.add(updatedCategory);

        }

        return updatedCategories;
    }


    @Override
    public void deleteCategoryById(Long id) throws SystemException {

        if (Objects.isNull(id)) {
            throw new SystemException(
                    "category.id.notNull"
            );
        }

        Category category = categoryRepo
                .findById(id)
                .orElseThrow(
                        () -> new SystemException(
                                "category.notFound"
                        )
                );

    /*
     لو Category عندها Products،
     نمنع الحذف بسبب الـForeign Key.
    */
        if (category.getProducts() != null
                && !category.getProducts().isEmpty()) {

            throw new SystemException(
                    "category.hasProducts"
            );
        }

        categoryRepo.delete(category);
    }




    @Override
    public void deleteCategoryListByIds(List<Long> ids) throws SystemException {
        if (Objects.isNull(ids) || ids.isEmpty()) {
            throw new SystemException(  "category.ids.notEmpty" );


        }

        for (Long id : ids) {
            deleteCategoryById(id);
        }
    }



    public CategoryDto toDto(Category category) {
        return categoryMapper.toDto(category);

    }
}