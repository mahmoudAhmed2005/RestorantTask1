package com.restorant_springdemo.restorant.mapper;

import com.restorant_springdemo.restorant.dto.CategoryDto;
import com.restorant_springdemo.restorant.model.Category;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "٢٠٢٦-١١-١٥T١٩:٤٩:٢٩+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25.0.2 (Eclipse Adoptium)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toEntity(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDto.getId() );
        category.setName( categoryDto.getName() );
        category.setLogo( categoryDto.getLogo() );
        category.setFlag( categoryDto.getFlag() );

        return category;
    }

    @Override
    public CategoryDto toDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId( category.getId() );
        categoryDto.setName( category.getName() );
        categoryDto.setLogo( category.getLogo() );
        categoryDto.setFlag( category.getFlag() );

        return categoryDto;
    }

    @Override
    public List<Category> toEntityList(List<CategoryDto> categoryDtos) {
        if ( categoryDtos == null ) {
            return null;
        }

        List<Category> list = new ArrayList<Category>( categoryDtos.size() );
        for ( CategoryDto categoryDto : categoryDtos ) {
            list.add( toEntity( categoryDto ) );
        }

        return list;
    }

    @Override
    public List<CategoryDto> toDtoList(List<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryDto> list = new ArrayList<CategoryDto>( categories.size() );
        for ( Category category : categories ) {
            list.add( toDto( category ) );
        }

        return list;
    }
}
