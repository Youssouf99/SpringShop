package com.ecommerceapi.ecommerceapi.dtos;


import lombok.Data;

@Data
public class CustomerDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
