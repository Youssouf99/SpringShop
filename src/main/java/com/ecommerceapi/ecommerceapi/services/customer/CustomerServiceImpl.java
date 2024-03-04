package com.ecommerceapi.ecommerceapi.services.customer;


import com.ecommerceapi.ecommerceapi.dtos.CustomerDto;
import com.ecommerceapi.ecommerceapi.entites.Command;
import com.ecommerceapi.ecommerceapi.entites.Customer;
import com.ecommerceapi.ecommerceapi.exceptions.CustomerNotFoundException;
import com.ecommerceapi.ecommerceapi.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.ecommerceapi.ecommerceapi.mappers.CustomerMapper.*;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto){
        Customer customer = toCustomer(customerDto);
        customer.setDateCreated(new Date());
        customerRepository.save(customer);
        return toCustomerDto(customer);
    }

    @Override
    public CustomerDto getCustomerById(String customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        return toCustomerDto(customer);
    }

    @Override
    public List<CustomerDto> getAllCustomers(){
        List <Customer> customers = customerRepository.findAll();
        List<CustomerDto> customerDtos = customers.stream()
                .map(customer ->
                     toCustomerDto(customer))
                .collect(Collectors.toList());

        return customerDtos;
    }


    @Override
    public void deleteCustomerById(String customerId){
        customerRepository.deleteById(customerId);
    }

    @Override
    public CustomerDto updateCustomerDto(String customerId, CustomerDto customerDto) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(" Customer not found"));
        updateCustomerFromDto(customerDto, customer);
        customerRepository.save(customer);
        return toCustomerDto(customer);
    }

    @Override
    public void customerExists(String customerId) throws CustomerNotFoundException {
        if (!customerRepository.existsById(customerId)){
            throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
        }

    }


    @Override
    public Customer findById(String customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        return customer;
    }








}
