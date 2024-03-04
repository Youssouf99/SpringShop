package com.ecommerceapi.ecommerceapi.controllers;

import com.ecommerceapi.ecommerceapi.dtos.CustomerDto;
import com.ecommerceapi.ecommerceapi.exceptions.CustomerNotFoundException;
import com.ecommerceapi.ecommerceapi.services.customer.CustomerService;
import com.ecommerceapi.ecommerceapi.services.customer.CustomerServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
@Slf4j
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/")
    public ResponseEntity<CustomerDto> createCustomer(
            @RequestBody CustomerDto customerDto){
        CustomerDto createdCustomer = customerService.saveCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @GetMapping("/")
    public List<CustomerDto> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(name = "id") String customerId){
        try {
            CustomerDto customerDto = customerService.getCustomerById(customerId);
            log.info("Customer found: {}", customerDto);
            return ResponseEntity.ok(customerDto);
        } catch (CustomerNotFoundException e) {
            log.error("Customer not found with ID: {}", customerId);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable(name = "id") String customerId,
                                                      @RequestBody CustomerDto customerDto){
        try {
            CustomerDto updatedCustomer = customerService.updateCustomerDto(customerId, customerDto);
            return ResponseEntity.ok(updatedCustomer);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable(name = "id") String customerId){
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.noContent().build();
    }





}
