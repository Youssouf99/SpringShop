package com.ecommerceapi.ecommerceapi.dtos;


import lombok.Data;

@Data
public class ProductDto {
    private String id;
    private String name;
    private String description;
    private double price;
    private String image;
    private int stockQuantity;

}
