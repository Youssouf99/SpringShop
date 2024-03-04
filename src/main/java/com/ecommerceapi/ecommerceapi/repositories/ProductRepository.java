package com.ecommerceapi.ecommerceapi.repositories;

import com.ecommerceapi.ecommerceapi.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
