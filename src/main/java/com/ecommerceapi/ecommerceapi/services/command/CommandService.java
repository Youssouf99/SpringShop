package com.ecommerceapi.ecommerceapi.services.command;

import com.ecommerceapi.ecommerceapi.dtos.CommandDto;
import com.ecommerceapi.ecommerceapi.exceptions.CommandNotFoundException;
import com.ecommerceapi.ecommerceapi.exceptions.CustomerNotFoundException;
import com.ecommerceapi.ecommerceapi.exceptions.ProductNotFoundException;

import java.util.List;

public interface CommandService {


    CommandDto createCommand(String customerId, String productId) throws CustomerNotFoundException, ProductNotFoundException;

    List<CommandDto> getCommandsFromCustomerId(String customerId) throws CustomerNotFoundException;

    CommandDto getCommandCustomerById(String custormerId, String commandId) throws CommandNotFoundException, CustomerNotFoundException;


    CommandDto addProductToCommand(String customerId, String commandId, String productId) throws CustomerNotFoundException, CommandNotFoundException, ProductNotFoundException;

    CommandDto updateCommand(String commandId, CommandDto commandDto) throws CommandNotFoundException;

    void commandExists(String commandId) throws CommandNotFoundException;

    void deleteCommandCustomerById(String customerId, String commandId) throws CommandNotFoundException, CustomerNotFoundException;
}
