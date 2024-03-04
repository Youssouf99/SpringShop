package com.ecommerceapi.ecommerceapi.controllers;

import com.ecommerceapi.ecommerceapi.dtos.CommandDto;
import com.ecommerceapi.ecommerceapi.exceptions.CommandNotFoundException;
import com.ecommerceapi.ecommerceapi.exceptions.CustomerNotFoundException;
import com.ecommerceapi.ecommerceapi.exceptions.ProductNotFoundException;
import com.ecommerceapi.ecommerceapi.services.command.CommandService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CommandController {

    public final CommandService commandService;

    @PostMapping( "/customer/{customerId}/commands/{productId}")
    public ResponseEntity<CommandDto> createCommand(
            @Parameter(description = "Customer ID", example = "33dcad72-a9f6-4b58-942a-120e1c80169a", required = false) @PathVariable String customerId,
            @Parameter(description = "Product ID", example = "5e302195-086a-4cad-8419-93995d7146ac", required = false) @PathVariable String productId) throws CustomerNotFoundException, ProductNotFoundException {

        CommandDto createdCommandDto = commandService.createCommand(customerId, productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCommandDto);
    }

    @GetMapping("/customer/{customerId}/commands")
    public ResponseEntity<List<CommandDto>> getCommandsCustomer(
            @Parameter(description = "Customer ID", example = "33dcad72-a9f6-4b58-942a-120e1c80169a")
            @PathVariable String customerId) {
        try {
            List<CommandDto> commandDtoList = commandService.getCommandsFromCustomerId(customerId);
            return ResponseEntity.ok(commandDtoList);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }


    @GetMapping("/customer/{customerId}/commands/{id}")
    public ResponseEntity<CommandDto> getCommandCustomerById(
            @Parameter(description = "Customer ID", example = "33dcad72-a9f6-4b58-942a-120e1c80169a")
            @PathVariable String customerId,
            @PathVariable(name = "id") String commandId){
        try {
            CommandDto commandDto = commandService.getCommandCustomerById(customerId, commandId);
            return ResponseEntity.ok(commandDto);
        } catch (CommandNotFoundException | CustomerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/customer/{customerId}/commands/{id}")
    public ResponseEntity<Void> deleteCommandCustomerById(
            @Parameter(description = "Customer ID", example = "33dcad72-a9f6-4b58-942a-120e1c80169a")
            @PathVariable String customerId,
            @PathVariable(name = "id") String commandId){
        try {
            commandService.deleteCommandCustomerById(customerId, commandId);
            return ResponseEntity.noContent().build();
        } catch (CommandNotFoundException | CustomerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping ("/customer/{customerId}/commands/{commandId}/{productId}")
    public ResponseEntity<CommandDto> addProductToCommand(
            @PathVariable String customerId,
            @PathVariable String commandId,
            @PathVariable String productId) {

        try {
            CommandDto updatedCommandDto = commandService.addProductToCommand(customerId, commandId, productId);
            return ResponseEntity.ok(updatedCommandDto);
        } catch (CustomerNotFoundException | CommandNotFoundException | ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
