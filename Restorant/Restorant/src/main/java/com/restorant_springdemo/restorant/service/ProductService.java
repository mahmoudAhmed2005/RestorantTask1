package com.restorant_springdemo.restorant.service;

import com.restorant_springdemo.restorant.dto.ProductDto;
import com.restorant_springdemo.restorant.model.Product;
import jakarta.transaction.SystemException;

import java.util.List;

public interface ProductService {

    List<ProductDto>getAllProducts();

    List<ProductDto> getProductsByCategoryId(Long categoryId) throws SystemException;



    /*
     تبحث عن المنتجات باستخدام كلمة موجودة
     في اسم المنتج أو وصف المنتج.
    */
    List<ProductDto> searchProductsByKey(  String key  ) throws SystemException;



    /*
     تضيف Product واحدة جديدة.
    */
    ProductDto saveProduct( ProductDto productDto ) throws SystemException;



    /*
     تضيف List من Products.
    */
    List<ProductDto> saveProductList( List<ProductDto> productDtos )throws SystemException;



    /*
     تعدّل Product واحدة موجودة.
    */
    ProductDto updateProduct(  ProductDto productDto  ) throws SystemException;



    /*
     تعدّل List من Products.
    */
    List<ProductDto> updateProductList( List<ProductDto> productDtos ) throws SystemException;



    /*
     تحذف Product واحدة باستخدام id.

     الـController هيستقبل id كـRequestParam.
    */
    void deleteProductById(Long id) throws SystemException;

    /*
     تحذف List من Products باستخدام ids.

     الـController هيستقبل الـids كـRequestParam.
    */
    void deleteProductListByIds( List<Long> ids  ) throws SystemException;




}
