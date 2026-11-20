package com.restorant_springdemo.restorant.mapper;

import com.restorant_springdemo.restorant.dto.ProductDto;
import com.restorant_springdemo.restorant.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/*
 componentModel = "spring" معناها إن MapStruct
 هتنشئ Implementation من الـMapper تلقائيًا،
 وSpring تقدر تعمل لها Injection داخل الـServiceImpl.
*/
@Mapper(componentModel = "spring")
public interface ProductMapper {

    /*
     تحول ProductDto إلى Product Entity.

     الحقول المتطابقة تتحول تلقائيًا:

     id          → id
     name        → name
     imagePath   → imagePath
     description → description
     price       → price

     نتجاهل category؛ لأن ProductDto عندها categoryId
     من نوع Long، بينما Product Entity عندها category
     من نوع Category.

     هنجيب Category باستخدام categoryId
     ونربطها بالـProduct داخل ProductServiceImpl.
    */
    @Mapping(target = "category", ignore = true)
    Product toEntity(ProductDto productDto);

    /*
     تحول Product Entity إلى ProductDto.

     category.id الموجودة داخل Product Entity
     تتحول إلى categoryId داخل ProductDto.

     category.name الموجودة داخل Product Entity
     تتحول إلى categoryName داخل ProductDto.
    */
    @Mapping( source = "category.id",target = "categoryId")



    @Mapping( source = "category.name", target = "categoryName")



    ProductDto toDto(Product product);

    /*
     تحول List من Product Entities
     إلى List من ProductDto.

     MapStruct هتستخدم toDto() لكل Product.
    */
    List<ProductDto> toDtoList(List<Product> products);

    /*
     تحول List من ProductDto
     إلى List من Product Entities.

     MapStruct هتستخدم toEntity() لكل ProductDto.

     ملاحظة:
     كل Product ناتج هيكون محتاج ربط Category
     داخل ProductServiceImpl.
    */
    List<Product> toEntityList(List<ProductDto> productDtos);
}