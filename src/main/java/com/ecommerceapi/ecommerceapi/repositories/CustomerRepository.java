package com.ecommerceapi.ecommerceapi.repositories;

import com.ecommerceapi.ecommerceapi.entites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
