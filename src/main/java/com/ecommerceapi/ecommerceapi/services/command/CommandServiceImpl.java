package com.ecommerceapi.ecommerceapi.services.command;

import com.ecommerceapi.ecommerceapi.dtos.CommandDto;
import com.ecommerceapi.ecommerceapi.entites.Command;
import com.ecommerceapi.ecommerceapi.entites.Customer;
import com.ecommerceapi.ecommerceapi.entites.Product;
import com.ecommerceapi.ecommerceapi.enums.StateCommand;
import com.ecommerceapi.ecommerceapi.exceptions.CommandNotFoundException;
import com.ecommerceapi.ecommerceapi.exceptions.CustomerNotFoundException;
import com.ecommerceapi.ecommerceapi.exceptions.ProductNotFoundException;
import com.ecommerceapi.ecommerceapi.mappers.CommandMapper;
import com.ecommerceapi.ecommerceapi.repositories.CommandRepository;
import com.ecommerceapi.ecommerceapi.services.customer.CustomerService;
import com.ecommerceapi.ecommerceapi.services.product.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class CommandServiceImpl implements CommandService {

    private final CommandRepository commandRepository;
    private final CommandMapper commandMapper;
    private final CustomerService customerService;
    private final ProductService productService;


    @Override
    public CommandDto createCommand(String customerId,
                                    String productId) throws CustomerNotFoundException, ProductNotFoundException {
        Product product = productService.findById(productId);
        Customer customer = customerService.findById(customerId);
        if (product.getStockQuantity() <= 0) {
            throw new ProductNotFoundException("Product not found with stock");
        }
        Command command = new Command();
        command.setDateCreated(new Date());
        command.setStateCommand(StateCommand.PRÉPARATION);
        command.setCustomer(customer);
        command.getProducts().add(product);
        command.setPriceTotal(product.getPrice());
        product.setStockQuantity(product.getStockQuantity()-1);
        commandRepository.save(command);
        return commandMapper.toCommandDto(command);
    }

    @Override
    public List<CommandDto> getCommandsFromCustomerId(String customerId) throws CustomerNotFoundException {
        customerService.customerExists(customerId);
        return commandRepository.findByCustomerId(customerId).stream()
                .map(commandMapper::toCommandDto).collect(Collectors.toList());

    }

    @Override
    public CommandDto getCommandCustomerById(String customerId, String commandId) throws CommandNotFoundException, CustomerNotFoundException {
        customerService.customerExists(customerId);
        commandExists(commandId);
        Command command = commandRepository.findByCustomerIdAndId(customerId, commandId);
        return commandMapper.toCommandDto(command);
    }

    @Override
    public void deleteCommandCustomerById(String customerId, String commandId) throws CommandNotFoundException, CustomerNotFoundException {
        customerService.customerExists(customerId);
        commandExists(commandId);
        commandRepository.deleteByCustomerIdAndId(customerId, commandId);
    }

    @Override
    public CommandDto addProductToCommand(String customerId, String commandId, String productId) throws CustomerNotFoundException, CommandNotFoundException, ProductNotFoundException {
        Command command = commandRepository.findByCustomerIdAndId(customerId, commandId);
        if (command == null){
            throw new CommandNotFoundException("Command not found for customer");
        }
        Product product = productService.findById(productId);
        if (product.getStockQuantity() <= 0) {
            throw new ProductNotFoundException("Product not found with available stock");
        }
        command.getProducts().add(product);
        command.setPriceTotal(command.getPriceTotal()+product.getPrice());
        product.setStockQuantity(product.getStockQuantity()-1);
        Command updatedCommand = commandRepository.save(command);
        return commandMapper.toCommandDto(updatedCommand);

    }

    @Override
    public CommandDto updateCommand(String commandId, CommandDto commandDto) throws CommandNotFoundException {
        Command existingCommand = commandRepository.findById(commandId)
                .orElseThrow(() -> new CommandNotFoundException("Command not found with ID: " + commandId));
        commandMapper.updateCommandFromDto(commandDto, existingCommand);
        commandRepository.save(existingCommand);
        return commandMapper.toCommandDto(existingCommand);
    }


    public Command findById(String id) throws CommandNotFoundException {
        return commandRepository.findById(id).orElseThrow(
                () -> new CommandNotFoundException("Command not found")
        );
    }


    @Override
    public void commandExists(String commandId) throws CommandNotFoundException {
        if (!commandRepository.existsById(commandId)) {
            throw new CommandNotFoundException("Command not found");
        }
    }


}