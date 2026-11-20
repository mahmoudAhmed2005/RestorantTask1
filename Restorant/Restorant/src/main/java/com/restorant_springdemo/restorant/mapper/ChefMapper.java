package com.restorant_springdemo.restorant.mapper;

import com.restorant_springdemo.restorant.dto.ChefDto;
import com.restorant_springdemo.restorant.model.Chef;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChefMapper {

    /*
    تحول ChefDto إلى Chef Entity.

    مش محتاجين @Mapping؛ لأن أسماء الحقول متطابقة:

    id        → id
    name      → name
    spec      → spec
    logoPath  → logoPath
    faceLink  → faceLink
    tweLink   → tweLink
    instaLink → instaLink
   */
    Chef toEntity(ChefDto chefDto);

    /*
     تحول Chef Entity إلى ChefDto.

     MapStruct هتنقل كل الحقول تلقائيًا
     لأن أسماءها وأنواعها متطابقة.
    */
    ChefDto toDto(Chef chef);

    /*
     تحول List من Chef Entities
     إلى List من ChefDto.

     MapStruct هتستخدم toDto()
     لتحويل كل عنصر في الـList.
    */
    List<ChefDto> toDtoList(List<Chef> chefs);

    /*
     تحول List من ChefDto
     إلى List من Chef Entities.

     MapStruct هتستخدم toEntity()
     لتحويل كل عنصر في الـList.
    */
    List<Chef> toEntityList(List<ChefDto> chefDtos);
}
