package com.restorant_springdemo.restorant.controller;

import com.restorant_springdemo.restorant.dto.ProductDto;
import com.restorant_springdemo.restorant.service.ProductService;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

@Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }



    @GetMapping
    public List<ProductDto>getAllProducts(){
    return productService.getAllProducts();
    }

    @PostMapping("/category")
    public List<ProductDto>getProductsByCategoryId(@Valid @RequestBody Long categoryId) throws SystemException {
    return productService.getProductsByCategoryId(categoryId);
    }

    @GetMapping("/search")
    public List<ProductDto>searchProductsByKey(@Valid @RequestParam String key) throws SystemException {
    return productService.searchProductsByKey(key);
    }

    @PostMapping
    public ProductDto saveProduct(@Valid @RequestBody ProductDto productDto) throws SystemException {
    return productService.saveProduct(productDto);
    }

    @PostMapping("/list")
    public List<ProductDto>saveProductList(@Valid @RequestBody List<ProductDto>productDtos) throws SystemException {
    return productService.saveProductList(productDtos);
    }

    @PutMapping
    public ProductDto updateProduct(@Valid @RequestBody ProductDto productDto) throws SystemException {
    return productService.updateProduct(productDto);
    }


    @PutMapping("/list")
    public List<ProductDto>updateProductList(@Valid @RequestBody List<ProductDto>productDtos) throws SystemException {
        return productService.updateProductList(productDtos);
    }


    @DeleteMapping
    public void deleteProductById(@Valid @RequestParam Long id) throws SystemException {
    productService.deleteProductById(id);
    }

    @DeleteMapping("/list")
    public void deleteProductListByIds(@Valid @RequestBody List<Long> ids) throws SystemException {
    productService.deleteProductListByIds(ids);
    }



}
