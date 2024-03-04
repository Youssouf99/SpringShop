package com.ecommerceapi.ecommerceapi.mappers;

import com.ecommerceapi.ecommerceapi.dtos.ProductDto;
import com.ecommerceapi.ecommerceapi.entites.Product;
import org.springframework.beans.BeanUtils;

public class ProductMapperImpl {

    public static ProductDto toProductDto(Product product){
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    public static Product toProduct(ProductDto productDto){
        Product product=new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }

}
