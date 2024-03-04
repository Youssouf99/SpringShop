package com.ecommerceapi.ecommerceapi.services.customer;

import com.ecommerceapi.ecommerceapi.dtos.CustomerDto;
import com.ecommerceapi.ecommerceapi.entites.Command;
import com.ecommerceapi.ecommerceapi.entites.Customer;
import com.ecommerceapi.ecommerceapi.exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {

    CustomerDto saveCustomer(CustomerDto customerDto);

    CustomerDto getCustomerById(String customerId) throws CustomerNotFoundException;

    List<CustomerDto> getAllCustomers();

    void deleteCustomerById(String customerId);

    CustomerDto updateCustomerDto(String customerId, CustomerDto customerDto) throws CustomerNotFoundException;

    void customerExists(String customerId) throws CustomerNotFoundException;

    Customer findById(String customerId) throws CustomerNotFoundException;

}
