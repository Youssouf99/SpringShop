package com.ecommerceapi.ecommerceapi.services.product;

import com.ecommerceapi.ecommerceapi.dtos.ProductDto;
import com.ecommerceapi.ecommerceapi.entites.Command;
import com.ecommerceapi.ecommerceapi.entites.Product;
import com.ecommerceapi.ecommerceapi.exceptions.ProductNotFoundException;

import java.util.List;

public interface ProductService {

    ProductDto saveProductByProductDto(ProductDto productDto);

    List<ProductDto> getAllProducts();

    ProductDto getProductById(String idProduct) throws ProductNotFoundException;

    void deleteProductById(String productId);

    ProductDto updateProduct(String productId, ProductDto updatedProductDto) throws ProductNotFoundException;

    Product findById(String productId);

    void productExists(String id) throws ProductNotFoundException;
}
