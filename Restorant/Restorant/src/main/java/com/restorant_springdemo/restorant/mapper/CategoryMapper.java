package com.restorant_springdemo.restorant.mapper;

import com.restorant_springdemo.restorant.dto.CategoryDto;
import com.restorant_springdemo.restorant.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "category", ignore = true)
    Category toEntity(CategoryDto categoryDto);

    @Mapping(target = "categoryId", ignore = true)
    @Mapping(target = "categoryName", ignore = true)
    CategoryDto toDto(Category category);

    List<Category>toEntityList(List<CategoryDto>categoryDtos);
    List<CategoryDto>toDtoList(List<Category>categories);


//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "products", ignore = true)
//    void updateEntityFromDto(
//            CategoryDto categoryDto,
//            @MappingTarget Category category
//    );
}
