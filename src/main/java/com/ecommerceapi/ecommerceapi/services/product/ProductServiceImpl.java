package com.ecommerceapi.ecommerceapi.services.product;

import com.ecommerceapi.ecommerceapi.dtos.ProductDto;
import com.ecommerceapi.ecommerceapi.entites.Product;
import com.ecommerceapi.ecommerceapi.exceptions.ProductNotFoundException;
import com.ecommerceapi.ecommerceapi.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.ProviderNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.ecommerceapi.ecommerceapi.mappers.ProductMapperImpl.toProduct;
import static com.ecommerceapi.ecommerceapi.mappers.ProductMapperImpl.toProductDto;

@Service
@AllArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    @Override
    public ProductDto saveProductByProductDto(ProductDto productDto){
        Product product = toProduct(productDto);
        product.setDateCreated(new Date());
        Product saveProduct = productRepository.save(product);
        return toProductDto(saveProduct);
    }
    @Override
    public List<ProductDto> getAllProducts(){
        List<Product> products = productRepository.findAll();
        List<ProductDto> collect =  products.stream().map(product ->
             toProductDto(product)
        ).collect(Collectors.toList());
        return collect;
    }
    @Override
    public ProductDto getProductById(String productId) throws ProductNotFoundException{
        Product product = productRepository.findById(productId).orElseThrow(
                () ->  new ProductNotFoundException("Product not found"));
        return toProductDto(product);
    }

    @Override
    public void deleteProductById(String productId){
        productRepository.deleteById(productId);
    }

    @Override
    public ProductDto updateProduct(String productId, ProductDto updatedProductDto) throws ProductNotFoundException {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        existingProduct.setName(updatedProductDto.getName());
        existingProduct.setDescription(updatedProductDto.getDescription());
        existingProduct.setPrice(updatedProductDto.getPrice());
        existingProduct.setImage(updatedProductDto.getImage());
        existingProduct.setStockQuantity(updatedProductDto.getStockQuantity());

        Product updateProduct = productRepository.save(existingProduct);

        return toProductDto(updateProduct);
    }

    @Override
    public Product findById(String productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProviderNotFoundException("Product not found"));

    }

    @Override
    public void productExists(String id) throws ProductNotFoundException {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found");
        }
    }


}
