package com.ecommerceapi.ecommerceapi.mappers;

import com.ecommerceapi.ecommerceapi.dtos.CommandDto;
import com.ecommerceapi.ecommerceapi.entites.Command;
import com.ecommerceapi.ecommerceapi.entites.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandMapper {

    public CommandDto toCommandDto(Command command){
        CommandDto commandDto = new CommandDto();
        BeanUtils.copyProperties(command, commandDto);
        if (command.getCustomer() != null){
            commandDto.setCustomerId(command.getCustomer().getId());
        }
        if (!command.getProducts().isEmpty()){
            List<String> productIds = command.getProducts().stream().map(Product::getId).collect(Collectors.toList());
            commandDto.setProductIds(productIds);
        }
       return commandDto;
    }

    public Command toCommand(CommandDto commandDto){
        Command command = new Command();
        BeanUtils.copyProperties(commandDto, command);

        return command;

    }

    public void updateCommandFromDto(CommandDto commandDto, Command command){
        BeanUtils.copyProperties(commandDto, command,"id", "dateCreated");
    }

}
