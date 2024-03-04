package com.ecommerceapi.ecommerceapi.mappers;

import com.ecommerceapi.ecommerceapi.dtos.CustomerDto;
import com.ecommerceapi.ecommerceapi.entites.Customer;
import com.ecommerceapi.ecommerceapi.exceptions.CustomerNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.Mapping;

@Service
public class CustomerMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static CustomerDto toCustomerDto(Customer customer){
        return modelMapper.map(customer, CustomerDto.class);

    }

    public static Customer toCustomer(CustomerDto customerDto){
        return modelMapper.map(customerDto, Customer.class);

    }

    public static void updateCustomerFromDto(CustomerDto customerDto, Customer customer){
        modelMapper.map(customerDto, customer);
    }
}
