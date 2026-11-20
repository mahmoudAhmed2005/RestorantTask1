package com.restorant_springdemo.restorant.service;


import com.restorant_springdemo.restorant.dto.CategoryDto;
import jakarta.transaction.SystemException;

import java.util.List;

/*
 CategoryService هي Interface بتحدد العمليات
 المطلوبة للتعامل مع Category.

 مفيش تنفيذ أو Business Logic هنا.
 التنفيذ الفعلي هيكون داخل CategoryServiceImpl.
*/
public interface CategoryService {

    /*
     ترجع كل Categories الموجودة
     داخل قاعدة البيانات.
    */

    List<CategoryDto>getAllCategory();


     /*
     تضيف Category واحدة جديدة.
    */


    CategoryDto saveCategory(CategoryDto categoryDto) throws SystemException;


    /*
     تضيف List من Categories مرة واحدة.
    */


    List<CategoryDto>saveCategoryList(List<CategoryDto>categoryDtos) throws SystemException;



    /*
     تعدّل Category واحدة موجودة.

     الـCategoryDto لازم تحتوي على id
     عشان نعرف الـCategory المطلوبة.
    */



    CategoryDto updateCategory(CategoryDto categoryDto) throws SystemException;



     /*
     تعدّل List من Categories.

     كل CategoryDto داخل الـList
     لازم تحتوي على id.
    */



    List<CategoryDto>updateCategoryList(List<CategoryDto>categoryDtos) throws SystemException;



    /*
    تحذف Category واحدة باستخدام id.

    الـController هيستقبل id كـRequestParam
    ويرسله إلى الـService.
   */
    void deleteCategoryById(Long id) throws SystemException;



    /*
   تحذف List من Categories باستخدام List من ids.

   الـController هيستقبل ids كـRequestParam
   ويرسلها إلى الـService.
  */
    void deleteCategoryListByIds(List<Long> ids) throws SystemException;

}
