package com.restorant_springdemo.restorant.repo;

import com.restorant_springdemo.restorant.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 Repository مسؤول عن التعامل مع جدول Product في قاعدة البيانات.

 JpaRepository بتوفر العمليات الأساسية الجاهزة، مثل:
 save()          → إضافة أو تعديل Product
 saveAll()       → إضافة أو تعديل List من Products
 findAll()       → إرجاع كل Products
 findById()      → البحث عن Product باستخدام id
 deleteById()    → حذف Product باستخدام id
 deleteAllById() → حذف List من Products باستخدام ids
 existsById()    → التأكد إن Product موجود
*/


@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {


     /*
     ترجع كل المنتجات التابعة لـCategory معينة.

     Category_Id معناها:
     ادخل على متغير category الموجود داخل Product،
     وابحث باستخدام id الموجود داخل Category.

     مثال:
     لو categoryId = 3
     هترجع كل المنتجات التي يكون category_id الخاص بها = 3.
    */

    List<Product> findAllByCategory_Id(Long categoryId);



    /*
         تبحث عن المنتجات باستخدام جزء من الاسم أو الوصف.

         Containing:
         معناها إن النص ممكن يكون جزءًا من الاسم أو الوصف.

         IgnoreCase:
         معناها عدم التفريق بين الحروف الكبيرة والصغيرة.

         Or:
         معناها يكفي أن تكون الكلمة موجودة في الاسم أو الوصف.

         ملاحظة:
         هنرسل نفس key مرتين من الـService:
         مرة للبحث في name، ومرة للبحث في desc.
        */
    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String nameKey,
            String descriptionKey
    );



    /*
        تتأكد هل يوجد Product بنفس الاسم،
        بدون التفريق بين الحروف الكبيرة والصغيرة.

        تستخدم عند إضافة Product جديد لمنع تكرار الاسم.
       */

    boolean existsByNameIgnoreCase(String name);


 /*
     تتأكد هل يوجد Product بنفس الاسم ولكن له id مختلف.

     تستخدم أثناء تعديل Product؛ عشان نستبعد المنتج
     الذي نعدّله من فحص تكرار الاسم.
    */

    boolean existsByNameIgnoreCaseAndIdNot(
            String name,
            Long id
    );

}
