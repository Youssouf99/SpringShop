package com.ecommerceapi.ecommerceapi.dtos;

import com.ecommerceapi.ecommerceapi.enums.StateCommand;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CommandDto {
    private String id;
    private Date dateCreated;
    private double priceTotal;
    private StateCommand stateCommand;
    private String customerId;
    private List<String> productIds = new ArrayList<>();


}
