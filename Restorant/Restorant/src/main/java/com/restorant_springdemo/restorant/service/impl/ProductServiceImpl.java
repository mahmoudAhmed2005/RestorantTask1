package com.restorant_springdemo.restorant.service.impl;

import com.restorant_springdemo.restorant.dto.ProductDto;
import com.restorant_springdemo.restorant.mapper.ProductMapper;
import com.restorant_springdemo.restorant.model.Category;
import com.restorant_springdemo.restorant.model.Product;
import com.restorant_springdemo.restorant.repo.CategoryRepo;
import com.restorant_springdemo.restorant.repo.ProductRepo;
import com.restorant_springdemo.restorant.service.ProductService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductMapper productMapper;
    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;

@Autowired
    public ProductServiceImpl(ProductMapper productMapper, ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productMapper = productMapper;
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }




    @Override
    public List<ProductDto> getAllProducts() {

        return productMapper.toDtoList(productRepo.findAll());

    }

    @Override
    public List<ProductDto> getProductsByCategoryId(Long categoryId) throws SystemException {
        if (Objects.isNull(categoryId)) {
            throw new SystemException("category.id.notNull");
        }

        if (!categoryRepo.existsById(categoryId)){
            throw new SystemException("category.notFound");
        }

        List<Product>products = productRepo.findAllByCategory_Id(categoryId);

       if (products.isEmpty()){
           throw new SystemException( "product.notFound");
       }

       return productMapper.toDtoList(products);
    }


    @Override
    public List<ProductDto> searchProductsByKey(String key) throws SystemException {
        if (Objects.isNull(key)|| key.isBlank()){
            throw new SystemException( "product.searchKey.notBlank");

        }

        String searchKey = key.trim();

        List<Product>products = productRepo.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                searchKey,searchKey
        );

        if (products.isEmpty()){
            throw new SystemException("product.notFound");
        }

        return productMapper.toDtoList(products);
    }



    @Override
    public ProductDto saveProduct(ProductDto productDto) throws SystemException {
        if (Objects.nonNull(productDto.getId())){
            throw new SystemException("product.id.mustBeNull");
        }

        if (productRepo.existsByNameIgnoreCase(productDto.getName())){
            throw new SystemException("product.name.exists");
        }

        if (Objects.isNull(productDto.getCategoryId())){
            throw new SystemException("category.id.notNull");
        }

        Category category = categoryRepo.findById(productDto.getCategoryId()).orElseThrow(()
                ->new SystemException("category.notFound"));

        Product product = productMapper.toEntity(productDto);
        product.setCategory(category);
        return toDto(productRepo.save(product));

    }

    @Override
    public List<ProductDto> saveProductList(List<ProductDto> productDtos) throws SystemException {

    if (Objects.isNull(productDtos)|| productDtos.isEmpty()){

        throw new SystemException("product.list.notEmpty");
    }

    List<ProductDto> savedProducts=new ArrayList<>();

    for (ProductDto productDto : productDtos){

        ProductDto savedProduct = saveProduct(productDto);
        savedProducts.add(savedProduct);
    }
    return savedProducts;
    }



    @Override
    public ProductDto updateProduct(ProductDto productDto) throws SystemException {
        if (Objects.nonNull(productDto.getId())){
            throw new SystemException("product.id.notNull");
        }

        if (!productRepo.existsById(productDto.getId())){
            throw new SystemException("product.notFound");
        }

        if (productRepo.existsByNameIgnoreCaseAndIdNot(productDto.getName(),productDto.getId())){
            throw new SystemException("product.name.exists");

        }

        if (Objects.isNull(productDto.getCategoryId())){
            throw new SystemException("category.id.notNull");
        }

        Category category = categoryRepo.findById(productDto.getCategoryId()).orElseThrow(
                ()->new SystemException("category.notFound"));

        Product product = productMapper.toEntity(productDto);
        product.setCategory(category);

        Product updateProduct = productRepo.save(product);

        return toDto(updateProduct);
    }

    @Override
    public List<ProductDto> updateProductList(List<ProductDto> productDtos) throws SystemException {

    if (Objects.isNull(productDtos)||productDtos.isEmpty()){
        throw new SystemException("product.list.notEmpty");
    }

    List<ProductDto>updateProducts = new ArrayList<>();

    for (ProductDto productDto : productDtos){

        ProductDto updatedProduct = updateProduct(productDto);
        updateProducts.add(updatedProduct);
    }
    return updateProducts;
    }

    @Override
    public void deleteProductById(Long id) throws SystemException {
  /*
         لازم id تكون موجودة.
        */
        if (Objects.isNull(id)) {
            throw new SystemException("product.id.notNull" );

        }

        /*
         نجيب Product عشان نتأكد إنها موجودة.
        */
        Product product = productRepo.findById(id).orElseThrow(
                () -> new SystemException("product.notFound" ));

        /*
         نحذف Product.

        حذف Product لا يحذف Category؛
         لأننا لم نستخدم Cascade Remove.
        */
        productRepo.delete(product);
    }

    @Override
    public void deleteProductListByIds(List<Long> ids) throws SystemException {

      /*
         نتأكد إن List الـids
         مش null ومش فاضية.
        */
        if (Objects.isNull(ids) || ids.isEmpty()) {
            throw new SystemException("product.ids.notEmpty");


        }

        /*
         نستخدم deleteProductById لكل id
         عشان نطبق فحص وجود الـProduct.
        */
        for (Long id : ids) {
            deleteProductById(id);
        }
    }


    public ProductDto toDto(Product product) {
        return productMapper.toDto(product);

    }
}
