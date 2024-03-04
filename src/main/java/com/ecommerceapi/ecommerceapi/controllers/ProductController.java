package com.ecommerceapi.ecommerceapi.controllers;

import com.ecommerceapi.ecommerceapi.dtos.ProductDto;
import com.ecommerceapi.ecommerceapi.exceptions.ProductNotFoundException;
import com.ecommerceapi.ecommerceapi.services.product.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping("/")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        ProductDto createdProductDto = productService.saveProductByProductDto(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductDto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable(name = "id") String productId)  {
         try {
             ProductDto productDto = productService.getProductById(productId);
             return ResponseEntity.ok(productDto);
         } catch (ProductNotFoundException e) {
             return ResponseEntity.notFound().build();
         }


    }

    @GetMapping("/")
    public List<ProductDto> getAllProducts(){
        return productService.getAllProducts();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") String productId){
        productService.deleteProductById(productId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(name = "id") String productId,
                                         @RequestBody ProductDto productDto) {
        try {
            ProductDto updatedProduct = productService.updateProduct(productId, productDto);
            return ResponseEntity.ok(updatedProduct);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }



}
