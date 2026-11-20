package com.restorant_springdemo.restorant.repo;

import com.restorant_springdemo.restorant.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
    /*
     Repository مسؤول عن التعامل مع جدول Category في قاعدة البيانات.

     JpaRepository بتوفر Methods جاهزة مثل:
     save()          → إضافة أو تعديل Category
     saveAll()       → إضافة أو تعديل List من Categories
     findAll()       → إرجاع كل Categories
     findById()      → البحث باستخدام id
     deleteById()    → الحذف باستخدام id
     deleteAllById() → حذف List باستخدام ids
     existsById()    → التأكد إن id موجود
    */





    /*
     تتأكد هل يوجد Category بنفس الاسم.

     IgnoreCase معناها عدم التفريق بين الحروف الكبيرة والصغيرة.

     مثال:
     Pizza و pizza و PIZZA يعتبروا نفس الاسم.

     تستخدم عند إضافة Category جديدة لمنع تكرار الاسم.
    */
    boolean existsByNameIgnoreCase(String name);





    /*
    تتأكد هل يوجد Category بنفس الاسم ولكن له id مختلف.

    تستخدم أثناء التعديل؛ لأننا نريد استبعاد الـCategory
    التي نعدّلها من عملية فحص تكرار الاسم.

    مثال:
    نعدّل Category رقم 1 واسمها Pizza.
    الشرط يبحث عن اسم Pizza في أي id ما عدا id رقم 1.
   */
    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);



}
