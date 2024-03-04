package com.ecommerceapi.ecommerceapi.repositories;

import com.ecommerceapi.ecommerceapi.entites.Command;
import com.ecommerceapi.ecommerceapi.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandRepository extends JpaRepository<Command, String> {
    List<Command> findByCustomerId(String customerId);
    Command findByCustomerIdAndId(String customerId, String id);
    void deleteByCustomerIdAndId(String customerId, String id);

}
